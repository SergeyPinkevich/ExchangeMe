package com.mockingbird.spinkevich.data.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import io.reactivex.Single
import javax.inject.Inject

class PersmissionManagerImpl @Inject constructor(
    private val context: Context
) : PermissionManager {

    override fun checkLocationPermission(success: () -> Unit) {
        checkPermission(success, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
    }

    private fun checkPermission(success: () -> Unit, permission: Array<String>) {
        permission.forEach {
            Single.fromCallable {
                ContextCompat.checkSelfPermission(context, it)
            }.map {
                if (it != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context as AppCompatActivity, permission, 1)
                } else {
                    success.invoke()
                }
            }
        }
    }
}