package com.mockingbird.spinkevich.data.utils.permission

import android.Manifest
import android.content.Context
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PermissionManagerImpl @Inject constructor(
    private val context: Context
) : PermissionManager {

    override fun checkLocationPermission(success: () -> Unit): Disposable {
        return checkPermission(success, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun checkPermission(success: () -> Unit, vararg permissions: String): Disposable {
        return Single.fromCallable {}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}