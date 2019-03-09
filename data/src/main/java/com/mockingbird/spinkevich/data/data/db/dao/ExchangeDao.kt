package com.mockingbird.spinkevich.data.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.mockingbird.spinkevich.data.data.db.entity.CountryEntity

@Dao
interface ExchangeDao {

    @Query("SELECT * from countries")
    fun getAll(): List<CountryEntity>

    @Insert(onConflict = REPLACE)
    fun insert(countryEntity: CountryEntity)

    @Delete
    fun delete(countryEntity: CountryEntity)
}