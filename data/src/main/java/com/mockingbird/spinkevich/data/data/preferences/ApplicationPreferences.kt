package com.mockingbird.spinkevich.data.data.preferences

import android.content.Context
import com.mockingbird.spinkevich.data.utils.string
import javax.inject.Inject

private const val FILE_NAME = "exchange.preferences"

private const val KEY_BASE_CURRENCY_CODE = ".base.country.code"

class ApplicationPreferences @Inject constructor(context: Context) {

    private val preferences by lazy { context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE) }

    var baseCountryCode by preferences.string(KEY_BASE_CURRENCY_CODE)
}