package com.mockingbird.spinkevich.exchangeme.feature.newcurrency

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.usecase.AllCountriesUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.addProgress
import com.mockingbird.spinkevich.exchangeme.utils.simplify
import com.mockingbird.spinkevich.exchangeme.utils.subscribeWithTimberError
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class NewCurrencyPresenter @Inject constructor(
    private val allCountriesUseCase: AllCountriesUseCase
) : BasePresenter<NewCurrencyView>() {

    private lateinit var countriesList: List<Country>

    fun loadCurrencies() {
        unsubscribeOnDestroy(
            allCountriesUseCase.getAllCountriesList()
                .observeOn(AndroidSchedulers.mainThread())
                .addProgress(viewState)
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