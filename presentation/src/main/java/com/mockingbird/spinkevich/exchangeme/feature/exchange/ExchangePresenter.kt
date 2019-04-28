package com.mockingbird.spinkevich.exchangeme.feature.exchange

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.entity.Rate
import com.mockingbird.spinkevich.domain.entity.Source
import com.mockingbird.spinkevich.domain.usecase.BaseCountryUseCase
import com.mockingbird.spinkevich.domain.usecase.ConvertedCountriesUseCase
import com.mockingbird.spinkevich.domain.usecase.RatesUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.subscribeWithTimberError
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import javax.inject.Inject

@InjectViewState
class ExchangePresenter @Inject constructor(
    private val baseCountryUseCase: BaseCountryUseCase,
    private val convertedCountriesUseCase: ConvertedCountriesUseCase,
    private val ratesUseCase: RatesUseCase
): BasePresenter<ExchangeView>() {

    private var isBaseCountryInitialized = false
    private var baseCountry: Country? = null
    private var convertedList: MutableList<Country> = mutableListOf()
    private var ratesList: MutableList<Rate> = mutableListOf()
    private var baseCurrencyAmount = 0F

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        observeRates()
    }

    private fun observeRates() {
        unsubscribeOnDestroy(
            ratesUseCase.getCurrentRates()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { viewState.ratesUpdatesWithError() }
                .subscribeWithTimberError { response ->
                    ratesList = response.rates.toMutableList()
                    if (response.source == Source.NETWORK) {
                        viewState.ratesUpdatesSuccessfully()
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
        } else {
            unsubscribeOnDestroy(
                convertedCountriesUseCase.addConvertedCountry(country)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWithTimberError {
                        convertedList.add(country)
                        convert(baseCurrencyAmount)
                    }
            )
        }
    }

    fun deleteCountry(country: Country) {
        unsubscribeOnDestroy(
            convertedCountriesUseCase.deleteConvertedCountry(country)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError {
                    convertedList.remove(country)
                    viewState.updateConvertedCountriesList(convertedList)
                }
        )
    }

    fun swapCountryWithBase(country: Country) {
        unsubscribeOnDestroy(
            baseCountryUseCase.getBaseCountry()
                .flatMap { baseCountry ->
                    Single.zip(
                        convertedCountriesUseCase.addConvertedCountry(baseCountry).andThen(Single.just(Any())),
                        convertedCountriesUseCase.deleteConvertedCountry(country).andThen(Single.just(Any())),
                        baseCountryUseCase.addBaseCountry(country).andThen(Single.just(Any())),
                        Function3 { _: Any, _: Any, _: Any -> }
                    )
                }
                .doOnError {  }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError {
                    initBaseCountry(country)
                    convertedList.add(baseCountry!!)
                    convertedList.remove(country)
                    viewState.updateConvertedCountriesList(convertedList)
                }
        )
    }

    fun convert(amount: Float) {
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