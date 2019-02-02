package com.mockingbird.spinkevich.exchangeme.core

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<View : MvpView> : MvpPresenter<View>() {

    private val compositeSubscription = CompositeDisposable()

    protected fun unsubscribeOnDestroy(disposable: Disposable) {
        compositeSubscription.add(disposable)
    }

    fun clearSubscriptions() {
        compositeSubscription.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeSubscription.clear()
    }
}