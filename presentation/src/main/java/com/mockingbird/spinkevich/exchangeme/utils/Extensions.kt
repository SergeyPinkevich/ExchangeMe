package com.mockingbird.spinkevich.exchangeme.utils

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.SearchView
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.mockingbird.spinkevich.exchangeme.core.ShowHideProgress
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import timber.log.Timber

fun FragmentActivity.addFragmentToStack(container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(container, fragment)
        .addToBackStack(null)
        .commit()
}

fun <T> Single<T>.subscribeWithTimberError(action: (T) -> Unit): Disposable {
    return this.subscribe({ action.invoke(it) }, { Timber.e(it) })
}

fun SearchView.onQueryTextChange(onQueryTextChange: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(query: String): Boolean {
            onQueryTextChange.invoke(query)
            return true
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }
    })
}

fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun <T> Single<T>.addProgress(showAndHideView: ShowHideProgress): Single<T> {
    return this.doOnSubscribe { showAndHideView.showProgress() }
        .doAfterTerminate { showAndHideView.hideProgress() }
}