package com.mockingbird.spinkevich.exchangeme.feature.exchange

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.analytics.AppAnalytics
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.entity.Rate
import com.mockingbird.spinkevich.domain.usecase.BaseCountryUseCase
import com.mockingbird.spinkevich.domain.usecase.ConvertedCountriesUseCase
import com.mockingbird.spinkevich.domain.usecase.OnBoardingUseCase
import com.mockingbird.spinkevich.domain.usecase.RatesUseCase
import com.mockingbird.spinkevich.domain.usecase.SwapCountriesUseCase
import com.mockingbird.spinkevich.domain.usecase.UpdateUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.subscribeWithTimberError
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class ExchangePresenter @Inject constructor(
    private val baseCountryUseCase: BaseCountryUseCase,
    private val convertedCountriesUseCase: ConvertedCountriesUseCase,
    private val swapCountriesUseCase: SwapCountriesUseCase,
    private val ratesUseCase: RatesUseCase,
    private val onBoardingUseCase: OnBoardingUseCase,
    private val updateUseCase: UpdateUseCase,
    private val appAnalytics: AppAnalytics
) : BasePresenter<ExchangeView>() {

    private var isBaseCountryInitialized = false
    private var baseCountry: Country? = null
    private var convertedList: MutableList<Country> = mutableListOf()
    private var ratesList: MutableList<Rate> = mutableListOf()
    private var baseCurrencyAmount = 0F

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateRates()
    }

    fun updateRates() {
        unsubscribeOnDestroy(
            ratesUseCase.getCurrentRatesFromDatabase()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError { ratesList = it.rates.toMutableList() }
        )

        if (updateUseCase.isNeedUpdateRates()) {
            unsubscribeOnDestroy(
                ratesUseCase.getCurrentRatesFromNetwork()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess { viewState.ratesUpdatesSuccessfully() }
                    .doOnError { viewState.ratesUpdatesWithError() }
                    .subscribeWithTimberError { ratesList = it.rates.toMutableList() }
            )
        }
    }

    private fun checkNeedShowOnboarding() {
        unsubscribeOnDestroy(
            onBoardingUseCase.isNeedShowOnBoarding()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError { isNeedShowOnBoarding ->
                    if (isNeedShowOnBoarding) {
                        viewState.showOnBoarding()
                        onBoardingUseCase.setLastTimeShownOnBoarding(System.currentTimeMillis())
                    } else {
                        viewState.hideOnBoarding()
                    }
                }
        )
    }

    fun init(baseCountry: Country) {
        initBaseCountry(baseCountry)
        observeConvertedCountries()
    }

    private fun initBaseCountry(baseCountry: Country) {
        unsubscribeOnDestroy(
            baseCountryUseCase.addBaseCountry(baseCountry)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError {
                    this.baseCountry = baseCountry
                    isBaseCountryInitialized = true
                    viewState.initializeBaseCountry(baseCountry)
                }
        )
    }

    private fun observeConvertedCountries() {
        unsubscribeOnDestroy(
            convertedCountriesUseCase.getConvertedCountriesList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError {
                    convertedList.addAll(it)
                    viewState.updateConvertedCountriesList(it)
                }
        )
    }

    fun addCurrencyMenuClicked() {
        val countriesAlreadyAdded = arrayListOf<Country>().apply {
            addAll(convertedList)
        }
        viewState.openNewCurrencyScreen(countriesAlreadyAdded)
    }

    fun addCountry(country: Country) {
        if (!isBaseCountryInitialized) {
            initBaseCountry(country)
            appAnalytics.logBaseCountryAdded(country)
        } else {
            checkNeedShowOnboarding()
            unsubscribeOnDestroy(
                convertedCountriesUseCase.addConvertedCountry(country)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWithTimberError {
                        convertedList.add(country)
                        convert(baseCurrencyAmount)
                    }
            )
            appAnalytics.logNewCurrencyAdded(country)
        }
    }

    fun deleteCountry(country: Country) {
        onBoardingUseCase.setNeedShowOnBoarding(false)
        unsubscribeOnDestroy(
            convertedCountriesUseCase.deleteConvertedCountry(country)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError {
                    convertedList.remove(country)
                    viewState.updateConvertedCountriesList(convertedList)
                    appAnalytics.logCurrencyDeleted(country)
                }
        )
    }

    fun swapCountryWithBase(country: Country) {
        onBoardingUseCase.setNeedShowOnBoarding(false)
        unsubscribeOnDestroy(
            swapCountriesUseCase.swapCountries(baseCountry!!, country)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError {
                    convertedList.remove(country)
                    convertedList.add(baseCountry!!)
                    baseCountry = country
                    viewState.initializeBaseCountry(baseCountry!!)
                    viewState.updateConvertedCountriesList(convertedList)
                    appAnalytics.logCurrenciesSwapped(baseCountry!!, country)
                }
        )
    }

    fun convert(amount: Float) {
        if (ratesList.isEmpty()) {
            viewState.ratesUpdatesWithError()
            return
        }
        baseCurrencyAmount = amount
        val baseRate = ratesList.find { it.currency == baseCountry?.currency?.code }
        convertedList.forEach { country ->
            val rate = ratesList.find { it.currency == country.currency.code }
            val updatedRate = rate?.rate!! / baseRate?.rate!!
            country.currency.amount = updatedRate * amount
        }
        viewState.updateConvertedCountriesList(convertedList)
    }
}