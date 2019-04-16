package com.mockingbird.spinkevich.data.repository.impl

import com.mockingbird.spinkevich.data.repository.UpdateRepository
import com.mockingbird.spinkevich.data.source.preferences.ApplicationPreferences
import javax.inject.Inject

class UpdateRepositoryImpl @Inject constructor(
    private val preferences: ApplicationPreferences
): UpdateRepository {

    override fun getLastTimeUpdateCountries(): Long {
        return preferences.lastTimeUpdateCountries
    }

    override fun setLastTimeUpdateCountries(time: Long) {
        preferences.lastTimeUpdateCountries = time
    }

    override fun getLastTimeUpdateRates(): Long {
        return preferences.lastTimeUpdateRates
    }

    override fun setLastTimeUpdateRates(time: Long) {
        preferences.lastTimeUpdateRates = time
    }
}