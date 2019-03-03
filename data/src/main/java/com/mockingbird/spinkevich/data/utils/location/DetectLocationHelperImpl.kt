package com.mockingbird.spinkevich.data.utils.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.text.TextUtils
import com.mockingbird.spinkevich.data.exceptions.UnknownLocationException
import timber.log.Timber
import javax.inject.Inject

class DetectLocationHelperImpl @Inject constructor(
    context: Context
) : DetectLocationHelper {

    private var currentLocation: Location? = null
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    override fun getLocation(): Location? {
        var provider: String? = LocationManager.GPS_PROVIDER
        if (!locationManager.isProviderEnabled(provider)) {
            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_FINE
            provider = locationManager.getBestProvider(criteria, true)
        }
        Timber.v("locationManager != null")
        if (provider == null) {
            return null
        } else {
            if (!TextUtils.isEmpty(provider)) {
                Timber.v("!TextUtils.isEmpty(provider)")
                detectLocation()
            } else {
                Timber.v("TextUtils.isEmpty(provider)")
            }
        }
        return currentLocation
    }

    @SuppressLint("MissingPermission")
    private fun detectLocation() {
        try {
            currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (currentLocation == null) {
                currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            }
            if (currentLocation == null) {
                currentLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        if (currentLocation == null) {
            throw UnknownLocationException()
        }
    }
}