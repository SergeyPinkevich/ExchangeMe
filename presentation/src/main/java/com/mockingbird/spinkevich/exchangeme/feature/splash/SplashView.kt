package com.mockingbird.spinkevich.exchangeme.feature.splash

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.mockingbird.spinkevich.domain.entity.Country

@StateStrategyType(AddToEndSingleStrategy::class)
interface SplashView : MvpView {

    fun openMainScreen(baseCountry: Country?)
}