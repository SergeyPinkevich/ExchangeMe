package com.mockingbird.spinkevich.data.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.mockingbird.spinkevich.data.data.db.entity.CountryEntity

@Dao
interface CountryDao {

    @Query("SELECT * FROM countries")
    fun getAllCountries(): List<CountryEntity>

    @Query("SELECT * FROM countries WHERE isConverted == 1")
    fun getConvertedCountries(): List<CountryEntity>

    @Query("SELECT * FROM countries WHERE isBase == 1")
    fun getBaseCountry(): CountryEntity

    @Insert(onConflict = REPLACE)
    fun insert(countryEntity: CountryEntity)

    @Delete
    fun delete(countryEntity: CountryEntity)
}