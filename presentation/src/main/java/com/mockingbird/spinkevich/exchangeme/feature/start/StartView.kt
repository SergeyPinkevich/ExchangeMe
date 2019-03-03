package com.mockingbird.spinkevich.exchangeme.feature.start

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.mockingbird.spinkevich.domain.entity.Country

interface StartView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun openExchangeScreen(baseCountry: Country)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun openAddCurrencyScreen()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showUnknownLocationError()
}