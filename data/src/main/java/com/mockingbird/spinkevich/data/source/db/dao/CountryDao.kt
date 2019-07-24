package com.mockingbird.spinkevich.data.source.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.mockingbird.spinkevich.data.source.db.entity.CountrySchema

@Dao
interface CountryDao {

    @Query("SELECT * FROM countries")
    fun getAllCountries(): List<CountrySchema>

    @Query("SELECT * FROM countries WHERE isConverted == 1")
    fun getConvertedCountries(): List<CountrySchema>

    @Query("SELECT * FROM countries WHERE isBase == 1")
    fun getBaseCountry(): CountrySchema?

    @Insert(onConflict = IGNORE)
    fun insert(countrySchema: CountrySchema)

    @Update
    fun update(countrySchema: CountrySchema)
}