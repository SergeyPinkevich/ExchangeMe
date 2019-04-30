package com.mockingbird.spinkevich.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.mockingbird.spinkevich.domain.entity.Country
import javax.inject.Inject

private const val LOCATION_PERMISSION_GRANTED_VALUE = "isGranted"
private const val LOCATION_PERMISSION_GRANTED_EVENT = "locationPermissionGranted"

private const val NEW_CURRENCY_SEARCH_BUTTON_CLICKED_EVENT = "newCurrencySearchButtonClicked"

private const val CURRENCY_CODE = "currencyCode"
private const val COUNTRY_NAME = "countryName"

private const val BASE_CURRENCY_ADDED_EVENT = "baseCurrencyAdded"
private const val NEW_CURRENCY_ADDED_EVENT = "newCurrencyAdded"
private const val CURRENCY_DELETED_EVENT = "currencyDeleted"
private const val CURRENCIES_SWAPPED_EVENT = "currenciesSwapped"

class AppAnalytics @Inject constructor(private val firebaseAnalytics: FirebaseAnalytics) {

    fun logLocationPermissionAnalyticEvent(permissionGranted: Boolean) {
        val bundle = Bundle().apply {
            putBoolean(LOCATION_PERMISSION_GRANTED_VALUE, permissionGranted)
        }
        firebaseAnalytics.logEvent(LOCATION_PERMISSION_GRANTED_EVENT, bundle)
    }

    fun logBaseCountryAdded(country: Country) {
        val bundle = getBundleWithCountryParameters(country)
        firebaseAnalytics.logEvent(BASE_CURRENCY_ADDED_EVENT, bundle)
    }

    fun logNewCurrencySearchButtonClicked() {
        firebaseAnalytics.logEvent(NEW_CURRENCY_SEARCH_BUTTON_CLICKED_EVENT, Bundle())
    }

    fun logNewCurrencyAdded(country: Country) {
        val bundle = getBundleWithCountryParameters(country)
        firebaseAnalytics.logEvent(NEW_CURRENCY_ADDED_EVENT, bundle)
    }

    fun logCurrencyDeleted(country: Country) {
        val bundle = getBundleWithCountryParameters(country)
        firebaseAnalytics.logEvent(CURRENCY_DELETED_EVENT, bundle)
    }

    fun logCurrenciesSwapped(baseCountry: Country, convertedCountry: Country) {
        val bundle = Bundle().apply {
            putString("${CURRENCY_CODE}1", baseCountry.currency.code)
            putString("${COUNTRY_NAME}1", baseCountry.englishName)
            putString("${CURRENCY_CODE}2", convertedCountry.currency.code)
            putString("${COUNTRY_NAME}2", convertedCountry.englishName)
        }
        firebaseAnalytics.logEvent(CURRENCIES_SWAPPED_EVENT, bundle)
    }

    private fun getBundleWithCountryParameters(country: Country): Bundle {
        return Bundle().apply {
            putString(CURRENCY_CODE, country.currency.code)
            putString(COUNTRY_NAME, country.englishName)
        }
    }
}