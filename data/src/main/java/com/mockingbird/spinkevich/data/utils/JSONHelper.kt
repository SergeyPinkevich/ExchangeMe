package com.mockingbird.spinkevich.data.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mockingbird.spinkevich.data.entity.CountriesInfo
import com.mockingbird.spinkevich.data.mapper.RestDataMapper
import com.mockingbird.spinkevich.domain.entity.Country
import timber.log.Timber
import javax.inject.Inject

class JSONHelper @Inject constructor(private val gson: Gson) {

    fun parse(jsonString: String): List<Country> {
        val info = readJson(jsonString)
        return info?.list?.map { RestDataMapper.convertToDomain(it) }!!
    }

    private fun readJson(jsonString: String): CountriesInfo? {
        return try {
            gson.fromJson(jsonString, CountriesInfo::class.java)
        } catch (e: JsonSyntaxException) {
            Timber.e(e)
            null
        }
    }
}