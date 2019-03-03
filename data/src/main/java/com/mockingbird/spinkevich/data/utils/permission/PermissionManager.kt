package com.mockingbird.spinkevich.data.utils.permission

import io.reactivex.disposables.Disposable

interface PermissionManager {

    fun checkLocationPermission(success: () -> Unit): Disposable
}