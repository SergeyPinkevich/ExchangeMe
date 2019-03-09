package com.mockingbird.spinkevich.exchangeme.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.mockingbird.spinkevich.data.data.db.ExchangeDatabase
import com.mockingbird.spinkevich.data.data.db.dao.ExchangeDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun providesDatabase(context: Context): ExchangeDatabase {
        return Room.databaseBuilder(
            context,
            ExchangeDatabase::class.java,
            "exchange.db")
            .build()
    }

    @Provides
    fun providesExchangeDao(database: ExchangeDatabase): ExchangeDao = database.exchangeDao()
}