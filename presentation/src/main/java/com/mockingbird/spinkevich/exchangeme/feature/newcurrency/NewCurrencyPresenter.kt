package com.mockingbird.spinkevich.exchangeme.feature.newcurrency

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.usecase.AllCountriesUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.addProgress
import com.mockingbird.spinkevich.exchangeme.utils.simplify
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class NewCurrencyPresenter @Inject constructor(
    private val allCountriesUseCase: AllCountriesUseCase
) : BasePresenter<NewCurrencyView>() {

    private var countriesList: List<Country>? = null

    fun loadCurrencies() {
        unsubscribeOnDestroy(
            allCountriesUseCase.getAllCountriesList()
                .observeOn(AndroidSchedulers.mainThread())
                .addProgress(viewState)
                .subscribe({
                    countriesList = it
                    viewState.showCountriesList(it)
                }, {
                    viewState.showError()
                })
        )
    }

    fun filterCurrencies(query: String) {
        countriesList?.let {
            val filteredList = countriesList?.filter {
                val filterByCurrencyName = it.name.simplify().contains(query.simplify())
                val filterByCurrencyCode = it.currency.name.simplify().contains(query.simplify())
                filterByCurrencyName || filterByCurrencyCode
            }
            viewState.showCountriesList(filteredList!!)
        }
    }
}