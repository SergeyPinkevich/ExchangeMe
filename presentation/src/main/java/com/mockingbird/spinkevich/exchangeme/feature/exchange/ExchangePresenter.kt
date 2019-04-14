package com.mockingbird.spinkevich.exchangeme.feature.exchange

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.entity.Rate
import com.mockingbird.spinkevich.domain.usecase.BaseCountryUseCase
import com.mockingbird.spinkevich.domain.usecase.ConvertedCountriesUseCase
import com.mockingbird.spinkevich.domain.usecase.RatesUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.subscribeWithTimberError
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class ExchangePresenter @Inject constructor(
    private val baseCountryUseCase: BaseCountryUseCase,
    private val convertedCountriesUseCase: ConvertedCountriesUseCase,
    private val ratesUseCase: RatesUseCase
): BasePresenter<ExchangeView>() {

    private var isBaseCountryInitialized = false
    private var convertedList: MutableList<Country> = mutableListOf()
    private var ratesList: MutableList<Rate> = mutableListOf()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        observeRates()
    }

    private fun observeRates() {
        unsubscribeOnDestroy(
            ratesUseCase.getCurrentRates()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError {
                    ratesList = it.toMutableList()
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
        viewState.openNewCurrencyScreen()
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
                        viewState.updateConvertedCountriesList(convertedList)
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError { baseCountry ->
                    convertedList.remove(country)
                    convertedList.add(baseCountry)
                    initBaseCountry(country)

                    convertedCountriesUseCase.addConvertedCountry(baseCountry).subscribe()
                    viewState.updateConvertedCountriesList(convertedList)
                }
        )
    }
}