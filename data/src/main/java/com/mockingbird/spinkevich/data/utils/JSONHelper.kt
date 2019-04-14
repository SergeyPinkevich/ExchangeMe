package com.mockingbird.spinkevich.data.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mockingbird.spinkevich.data.entity.CountriesInfo
import com.mockingbird.spinkevich.data.entity.RateResponse
import com.mockingbird.spinkevich.data.mapper.rest.CountryRestMapper
import com.mockingbird.spinkevich.data.mapper.rest.RateRestMapper
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.entity.Rate
import timber.log.Timber
import javax.inject.Inject

class JSONHelper @Inject constructor(private val gson: Gson) {

    fun parseCountries(jsonString: String): List<Country> {
        val response = readCountryJson(jsonString)
        return response?.list?.map { CountryRestMapper.convertToDomain(it) } ?: emptyList()
    }

    fun parseRates(jsonString: String): List<Rate> {
        val response = readRateJson(jsonString)
        return response?.rates?.map { RateRestMapper.convertToDomain(it) } ?: emptyList()
    }

    private fun readRateJson(jsonString: String): RateResponse? {
        return try {
            gson.fromJson(jsonString, RateResponse::class.java)
        } catch (e: JsonSyntaxException) {
            Timber.e(e)
            null
        }
    }

    private fun readCountryJson(jsonString: String): CountriesInfo? {
        return try {
            gson.fromJson(jsonString, CountriesInfo::class.java)
        } catch (e: JsonSyntaxException) {
            Timber.e(e)
            null
        }
    }
}