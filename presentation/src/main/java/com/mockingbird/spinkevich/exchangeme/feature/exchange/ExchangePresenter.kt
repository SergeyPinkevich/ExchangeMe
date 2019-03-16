package com.mockingbird.spinkevich.exchangeme.feature.exchange

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.usecase.BaseCountryUseCase
import com.mockingbird.spinkevich.domain.usecase.ConvertedCountriesUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.subscribeWithTimberError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class ExchangePresenter @Inject constructor(
    private val baseCountryUseCase: BaseCountryUseCase,
    private val convertedCountriesUseCase: ConvertedCountriesUseCase
): BasePresenter<ExchangeView>() {

    private var isBaseCountryInitialized = false
    private var convertedList: MutableList<Country> = mutableListOf()

    fun init(country: Country) {
        isBaseCountryInitialized = true
        viewState.initializeBaseCountry(country)
        unsubscribeOnDestroy(
            baseCountryUseCase.addBaseCountry(country)
                .observeOn(Schedulers.io())
                .subscribeWithTimberError {}
        )
        unsubscribeOnDestroy(
            convertedCountriesUseCase.getConvertedCountriesList()
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
            unsubscribeOnDestroy(
                baseCountryUseCase.addBaseCountry(country)
                    .subscribeWithTimberError {
                        isBaseCountryInitialized = true
                        viewState.initializeBaseCountry(country)
                    }
            )
        } else {
            unsubscribeOnDestroy(
                convertedCountriesUseCase.addConvertedCountry(country)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWithTimberError {
                        convertedList.add(country)
                        viewState.updateCountriesList(convertedList)
                    }
            )
        }
    }

    fun removeCountry(position: Int) {
        val country = convertedList[position]
        unsubscribeOnDestroy(
            convertedCountriesUseCase.deleteConvertedCountry(country)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError {
                    convertedList.remove(country)
                    viewState.updateCountriesList(convertedList)
                }
        )
    }
}