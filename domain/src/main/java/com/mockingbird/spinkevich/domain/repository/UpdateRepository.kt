package com.mockingbird.spinkevich.domain.repository

interface UpdateRepository {

    fun getLastTimeUpdate(): Long

    fun setLastTimeUpdate(time: Long)
}