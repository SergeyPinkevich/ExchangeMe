package com.mockingbird.spinkevich.data.data.preferences

import android.content.Context
import com.mockingbird.spinkevich.data.utils.boolean
import javax.inject.Inject

private const val FILE_NAME = "exchange.preferences"

private const val KEY_BASE_CURRENCY_SELECTED = ".base.currency.selected"
private const val DEFAULT_BASE_CURRENCY_SELECTED = false

class ApplicationPreferences @Inject constructor(context: Context) {

    private val preferences by lazy { context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE) }

    var baseCurrencySelected by preferences.boolean(KEY_BASE_CURRENCY_SELECTED, DEFAULT_BASE_CURRENCY_SELECTED)
}