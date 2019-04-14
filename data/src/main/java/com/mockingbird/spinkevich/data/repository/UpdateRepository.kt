package com.mockingbird.spinkevich.data.repository

interface UpdateRepository {

    fun getLastTimeUpdate(): Long

    fun setLastTimeUpdate(time: Long)
}