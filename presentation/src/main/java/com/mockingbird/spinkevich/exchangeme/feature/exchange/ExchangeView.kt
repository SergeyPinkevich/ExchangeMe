package com.mockingbird.spinkevich.exchangeme.feature.exchange

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.mockingbird.spinkevich.domain.entity.Country

interface ExchangeView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initializeBaseCountry(country: Country)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openNewCurrencyScreen(convertedCountries: ArrayList<Country>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateConvertedCountriesList(convertedCountries: List<Country>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun ratesUpdatesSuccessfully()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun ratesUpdatesWithError()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showOnBoarding()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideOnBoarding()
}