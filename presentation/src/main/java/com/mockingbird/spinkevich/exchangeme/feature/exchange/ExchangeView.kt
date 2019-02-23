package com.mockingbird.spinkevich.exchangeme.feature.exchange

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.mockingbird.spinkevich.domain.entity.Country

interface ExchangeView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initializeBaseCountry(country: Country)
}