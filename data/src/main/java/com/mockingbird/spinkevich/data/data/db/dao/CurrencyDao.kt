package com.mockingbird.spinkevich.data.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.mockingbird.spinkevich.data.data.db.entity.CurrencySchema

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currencies WHERE id == :code")
    fun getCurrency(code: String): CurrencySchema

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencySchema: CurrencySchema)

    @Delete
    fun delete(currencySchema: CurrencySchema)
}