package com.mockingbird.spinkevich.exchangeme.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.mockingbird.spinkevich.data.source.db.ExchangeDatabase
import com.mockingbird.spinkevich.data.source.db.dao.CountryDao
import com.mockingbird.spinkevich.data.source.db.dao.CurrencyDao
import com.mockingbird.spinkevich.data.source.db.dao.RateDao
import dagger.Module
import dagger.Provides

private const val DATABASE_NAME = "exchange.db"

@Module
class DatabaseModule {

    @Provides
    fun providesDatabase(context: Context): ExchangeDatabase {
        return Room.databaseBuilder(
            context,
            ExchangeDatabase::class.java,
            DATABASE_NAME)
            .build()
    }

    @Provides
    fun providesCountryDao(database: ExchangeDatabase): CountryDao = database.countryDao()

    @Provides
    fun providesCurrencyDao(database: ExchangeDatabase): CurrencyDao = database.currencyDao()

    @Provides
    fun providesRateDao(database: ExchangeDatabase): RateDao = database.rateDao()
}