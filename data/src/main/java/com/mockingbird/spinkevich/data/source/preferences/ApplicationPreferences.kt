package com.mockingbird.spinkevich.data.source.preferences

import android.content.Context
import com.mockingbird.spinkevich.data.utils.boolean
import com.mockingbird.spinkevich.data.utils.long
import javax.inject.Inject

private const val FILE_NAME = "exchange.preferences"

private const val KEY_LAST_TIME_UPDATE_COUNTRIES = ".last.time.update.countries"
private const val KEY_LAST_TIME_UPDATE_RATES = ".last.time.update.rates"
private const val KEY_LAST_TIME_SHOW_ON_BOARDING = ".last.time.show.onboarding"
private const val KEY_NEED_SHOW_ON_BOARDING = ".need.show.on.boarding"

class ApplicationPreferences @Inject constructor(context: Context) {

    private val preferences by lazy { context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE) }

    var lastTimeUpdateCountries by preferences.long(KEY_LAST_TIME_UPDATE_COUNTRIES)
    var lastTimeUpdateRates by preferences.long(KEY_LAST_TIME_UPDATE_RATES)
    var lastTimeShowOnBoardding by preferences.long(KEY_LAST_TIME_SHOW_ON_BOARDING)
    var isNeedShowOnBoarding by preferences.boolean(KEY_NEED_SHOW_ON_BOARDING, true)
}