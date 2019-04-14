package com.mockingbird.spinkevich.data.source.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.mockingbird.spinkevich.data.source.db.dao.CountryDao
import com.mockingbird.spinkevich.data.source.db.dao.CurrencyDao
import com.mockingbird.spinkevich.data.source.db.dao.RateDao
import com.mockingbird.spinkevich.data.source.db.entity.CountrySchema
import com.mockingbird.spinkevich.data.source.db.entity.CurrencySchema
import com.mockingbird.spinkevich.data.source.db.entity.RateSchema

@Database(entities = [CountrySchema::class, CurrencySchema::class, RateSchema::class], version = 1)
abstract class ExchangeDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    abstract fun currencyDao(): CurrencyDao

    abstract fun rateDao(): RateDao
}