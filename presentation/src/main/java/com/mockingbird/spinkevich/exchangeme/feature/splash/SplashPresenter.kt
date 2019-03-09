package com.mockingbird.spinkevich.exchangeme.feature.splash

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.data.data.preferences.ApplicationPreferences
import com.mockingbird.spinkevich.domain.usecase.CountriesListUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.subscribeWithTimberError
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val preferences: ApplicationPreferences,
    private val countriesListUseCase: CountriesListUseCase
) : BasePresenter<SplashView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val baseCurrencyCode = preferences.baseCountryCode
        if (baseCurrencyCode.isNotEmpty()) {
            unsubscribeOnDestroy(
                countriesListUseCase.getAllCountriesList()
                    .subscribeWithTimberError { countriesList ->
                        val baseCountry = countriesList.first { it.code == baseCurrencyCode }
                        viewState.openMainScreen(baseCountry)
                    }
            )
        } else {
            viewState.openMainScreen(null)
        }
    }
}