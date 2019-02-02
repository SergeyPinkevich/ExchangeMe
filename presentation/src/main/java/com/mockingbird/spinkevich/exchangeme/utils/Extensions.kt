package com.mockingbird.spinkevich.exchangeme.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.SearchView
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