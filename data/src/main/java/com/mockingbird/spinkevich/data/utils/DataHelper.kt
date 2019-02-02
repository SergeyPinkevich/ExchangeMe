package com.mockingbird.spinkevich.data.utils

import android.content.Context
import com.google.gson.Gson
import com.mockingbird.spinkevich.data.entity.CountriesInfo
import java.io.IOException
import javax.inject.Inject

private const val CURRENCY_FILE_NAME = "data.json"

class DataHelper @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {

    fun getCurrencyLocalInfo(): CountriesInfo? {
        val json: String
        try {
            val inputStream = context.assets?.open(CURRENCY_FILE_NAME)
            val size = inputStream?.available()
            val buffer = ByteArray(size!!)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
            return gson.fromJson(json, CountriesInfo::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}