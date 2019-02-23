package com.mockingbird.spinkevich.data.utils

interface PermissionManager {

    fun checkLocationPermission(success: () -> Unit)
}