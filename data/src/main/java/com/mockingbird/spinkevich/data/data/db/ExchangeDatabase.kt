package com.mockingbird.spinkevich.data.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.mockingbird.spinkevich.data.data.db.dao.ExchangeDao
import com.mockingbird.spinkevich.data.data.db.entity.CountryEntity

@Database(entities = [CountryEntity::class], version = 1)
abstract class ExchangeDatabase : RoomDatabase() {

    abstract fun exchangeDao(): ExchangeDao
}