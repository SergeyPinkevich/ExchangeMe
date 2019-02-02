package com.mockingbird.spinkevich.exchangeme.feature.newcurrency

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.domain.entity.model.Country
import com.mockingbird.spinkevich.domain.usecase.CountriesListUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
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
            countriesListUseCase.getCountriesList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithTimberError {
                    countriesList = it
                    viewState.showCountriesList(it)
                }
        )
    }

    fun filterCurrencies(query: String) {
        val filteredList = countriesList.filter { it.name.trim().toLowerCase().contains(query.trim().toLowerCase()) || it.currencies[0].name.trim().toLowerCase().contains(query.trim().toLowerCase()) }
        viewState.showCountriesList(filteredList)
    }
}