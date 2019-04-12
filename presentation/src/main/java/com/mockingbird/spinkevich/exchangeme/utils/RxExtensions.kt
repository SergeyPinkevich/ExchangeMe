package com.mockingbird.spinkevich.exchangeme.utils

import com.mockingbird.spinkevich.exchangeme.core.ShowHideProgress
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import timber.log.Timber

fun <T> Single<T>.subscribeWithTimberError(action: (T) -> Unit): Disposable {
    return this.subscribe({ action.invoke(it) }, { Timber.e(it) })
}

fun Completable.subscribeWithTimberError(action: () -> Unit): Disposable {
    return this.subscribe({ action.invoke() }, { Timber.e(it) })
}

fun <T> Single<T>.addProgress(showAndHideView: ShowHideProgress): Single<T> {
    return this.doOnSubscribe { showAndHideView.showProgress() }
        .doAfterTerminate { showAndHideView.hideProgress() }
}