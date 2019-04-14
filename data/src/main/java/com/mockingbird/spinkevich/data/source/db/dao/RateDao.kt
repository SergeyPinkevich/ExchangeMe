package com.mockingbird.spinkevich.data.source.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.mockingbird.spinkevich.data.source.db.entity.RateSchema

@Dao
interface RateDao {

    @Query("SELECT * FROM rates")
    fun getRates(): List<RateSchema>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rate: RateSchema)
}