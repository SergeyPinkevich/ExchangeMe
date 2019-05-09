package com.mockingbird.spinkevich.exchangeme.feature.core

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor() : BasePresenter<MainView>() {

    private var backButtonClickedTwice = false

    fun checkBackButtonClickedTwice() {
        if (backButtonClickedTwice) {
            viewState.closeApp()
        } else {
            viewState.showClickBackButtonAgain()
            backButtonClickedTwice = true
        }
    }
}