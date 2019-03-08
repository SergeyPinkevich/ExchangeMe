package com.mockingbird.spinkevich.exchangeme.feature.exchange

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.data.utils.DataHelper
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.usecase.CurrentRatesUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.subscribeWithTimberError
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class ExchangePresenter @Inject constructor(
    private val currentRatesUseCase: CurrentRatesUseCase,
    private val dataHelper: DataHelper
): BasePresenter<ExchangeView>() {

    private var isBaseCountryInitialized = false
    private var convertedList: MutableList<Country> = mutableListOf()

    fun init(country: Country) {
        isBaseCountryInitialized = true
        viewState.initializeBaseCountry(country)
        unsubscribeOnDestroy(
            currentRatesUseCase.getCurrentRates(country.currencies.first().code.toLowerCase())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { Timber.e(it) }
                .subscribeWithTimberError {
                    dataHelper.parseRate(it, "usd")
                }
        )
    }

    fun addCurrencyMenuClicked() {
        viewState.openNewCurrencyScreen()
    }

    fun addCountry(country: Country) {
        if (!isBaseCountryInitialized) {
            isBaseCountryInitialized = true
            viewState.initializeBaseCountry(country)
        } else {
            convertedList.add(country)
            viewState.updateCountriesList(convertedList)
        }
    }
}