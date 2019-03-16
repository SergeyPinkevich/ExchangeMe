package com.mockingbird.spinkevich.exchangeme.feature.start

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.core.ShowHideProgress

interface StartView : MvpView, ShowHideProgress {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openExchangeScreen(baseCountry: Country)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun openAddCurrencyScreen()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showUnknownLocationError()
}