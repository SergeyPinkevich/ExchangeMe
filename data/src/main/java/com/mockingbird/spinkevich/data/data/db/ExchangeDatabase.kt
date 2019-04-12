package com.mockingbird.spinkevich.data.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.mockingbird.spinkevich.data.data.db.dao.CountryDao
import com.mockingbird.spinkevich.data.data.db.dao.CurrencyDao
import com.mockingbird.spinkevich.data.data.db.entity.CountrySchema
import com.mockingbird.spinkevich.data.data.db.entity.CurrencySchema

@Database(entities = [CountrySchema::class, CurrencySchema::class], version = 1)
abstract class ExchangeDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    abstract fun currencyDao(): CurrencyDao
}