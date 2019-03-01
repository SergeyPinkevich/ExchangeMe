package com.mockingbird.spinkevich.exchangeme.feature.exchange

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.data.utils.DataHelper
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.entity.Currency
import com.mockingbird.spinkevich.domain.usecase.CurrentRatesUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.subscribeWithTimberError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class ExchangePresenter @Inject constructor(
    private val currentRatesUseCase: CurrentRatesUseCase,
    private val dataHelper: DataHelper
): BasePresenter<ExchangeView>() {

    private lateinit var baseCountry: Country
    private var convertedList: MutableList<Country> = mutableListOf()

    init {
        baseCountry = Country("RussianFederation", "Europe", "Eastern Europe", "", listOf(Currency("RUB", "Russian rouble", "₽")))
        viewState.initializeBaseCountry(baseCountry)
        currentRatesUseCase.getCurrentRates(baseCountry.currencies.first().code.toLowerCase())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Timber.e(it) }
            .subscribeWithTimberError {
                dataHelper.parseRate(it, "usd")
            }
    }

    fun addCurrencyMenuClicked() {
        viewState.openNewCurrencyScreen()
    }

    fun addCountry(country: Country) {
        convertedList.add(country)
        viewState.updateCountriesList(convertedList)
    }

    fun removeCountry(country: Country) {
        convertedList.remove(country)
    }
}