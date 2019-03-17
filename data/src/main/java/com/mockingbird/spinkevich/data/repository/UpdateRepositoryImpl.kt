package com.mockingbird.spinkevich.data.repository

import com.mockingbird.spinkevich.data.data.preferences.ApplicationPreferences
import com.mockingbird.spinkevich.domain.repository.UpdateRepository
import javax.inject.Inject

class UpdateRepositoryImpl @Inject constructor(
    private val preferences: ApplicationPreferences
): UpdateRepository {

    override fun getLastTimeUpdate(): Long {
        return preferences.lastTimeUpdate
    }

    override fun setLastTimeUpdate(time: Long) {
        preferences.lastTimeUpdate = time
    }
}