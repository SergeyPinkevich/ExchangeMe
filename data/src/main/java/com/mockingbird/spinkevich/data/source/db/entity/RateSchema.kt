package com.mockingbird.spinkevich.data.source.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "rates")
data class RateSchema(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name = "currency")
    var currency: String,
    @ColumnInfo(name = "rate")
    var rate: Float
)