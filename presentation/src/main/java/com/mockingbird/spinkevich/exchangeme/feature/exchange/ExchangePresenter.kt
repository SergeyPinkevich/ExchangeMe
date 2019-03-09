package com.mockingbird.spinkevich.exchangeme.feature.exchange

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.data.data.preferences.ApplicationPreferences
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.usecase.ConvertedCountriesListUseCase
import com.mockingbird.spinkevich.domain.usecase.CurrentRatesUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.subscribeWithTimberError
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class ExchangePresenter @Inject constructor(
    private val currentRatesUseCase: CurrentRatesUseCase,
    private val convertedCountriesListUseCase: ConvertedCountriesListUseCase,
    private val preferences: ApplicationPreferences
): BasePresenter<ExchangeView>() {

    private var isBaseCountryInitialized = false
    private var convertedList: MutableList<Country> = mutableListOf()

    fun init(country: Country) {
        isBaseCountryInitialized = true
        viewState.initializeBaseCountry(country)
        unsubscribeOnDestroy(
            convertedCountriesListUseCase.getConvertedCountriesList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError {
                    viewState.updateCountriesList(it)
                }
        )
    }

    fun addCurrencyMenuClicked() {
        viewState.openNewCurrencyScreen()
    }

    fun addCountry(country: Country) {
        if (!isBaseCountryInitialized) {
            isBaseCountryInitialized = true
            preferences.baseCountryCode = country.code
            viewState.initializeBaseCountry(country)
        } else {
            convertedList.add(country)
            unsubscribeOnDestroy(
                convertedCountriesListUseCase.addConvertedCountry(country)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { viewState.updateCountriesList(convertedList) }
            )
        }
    }
}