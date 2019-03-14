package com.mockingbird.spinkevich.exchangeme.feature.splash

import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.domain.usecase.BaseCountryUseCase
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.subscribeWithTimberError
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val baseCountryUseCase: BaseCountryUseCase
) : BasePresenter<SplashView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        unsubscribeOnDestroy(
            baseCountryUseCase.getBaseCountry()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { viewState.openMainScreen(null) }
                .subscribeWithTimberError {
                    viewState.openMainScreen(it)
                }
        )
    }
}