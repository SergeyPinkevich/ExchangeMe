package com.mockingbird.spinkevich.exchangeme.feature.newcurrency

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.usecase.CountriesListUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.simplify
import com.mockingbird.spinkevich.exchangeme.utils.subscribeWithTimberError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class NewCurrencyPresenter @Inject constructor(
    private val countriesListUseCase: CountriesListUseCase
) : BasePresenter<NewCurrencyView>() {

    private lateinit var countriesList: List<Country>

    fun loadCurrencies() {
        unsubscribeOnDestroy(
            countriesListUseCase.getAllCountriesList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError {
                    countriesList = it
                    viewState.showCountriesList(it)
                }
        )
    }

    fun filterCurrencies(query: String) {
        val filteredList = countriesList.filter {
            val filterByCurrencyName = it.name.simplify().contains(query.simplify())
            val filterByCurrencyCode = it.currencies[0].name.simplify().contains(query.simplify())
            filterByCurrencyName || filterByCurrencyCode
        }
        viewState.showCountriesList(filteredList)
    }
}