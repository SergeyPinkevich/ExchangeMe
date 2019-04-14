package com.mockingbird.spinkevich.data.source.preferences

import android.content.Context
import com.mockingbird.spinkevich.data.utils.long
import javax.inject.Inject

private const val FILE_NAME = "exchange.preferences"

private const val KEY_LAST_TIME_UPDATE = ".last.time.update"

class ApplicationPreferences @Inject constructor(context: Context) {

    private val preferences by lazy { context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE) }

    var lastTimeUpdate by preferences.long(KEY_LAST_TIME_UPDATE)
}