package com.mockingbird.spinkevich.exchangeme.feature.core

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface MainView : MvpView {

    fun showClickBackButtonAgain()

    fun closeApp()
}