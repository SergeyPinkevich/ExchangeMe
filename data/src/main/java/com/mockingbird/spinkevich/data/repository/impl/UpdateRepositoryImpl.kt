package com.mockingbird.spinkevich.data.repository.impl

import com.mockingbird.spinkevich.data.repository.UpdateRepository
import com.mockingbird.spinkevich.data.source.preferences.ApplicationPreferences
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