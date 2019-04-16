package com.mockingbird.spinkevich.data.repository

interface UpdateRepository {

    fun getLastTimeUpdateCountries(): Long

    fun setLastTimeUpdateCountries(time: Long)

    fun getLastTimeUpdateRates(): Long

    fun setLastTimeUpdateRates(time: Long)
}