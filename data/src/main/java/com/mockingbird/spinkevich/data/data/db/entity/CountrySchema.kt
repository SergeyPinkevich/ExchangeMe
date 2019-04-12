package com.mockingbird.spinkevich.data.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "countries")
data class CountrySchema(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo(name = "isBase")
    var isBase: Boolean,
    @ColumnInfo(name = "isConverted")
    var isConverted: Boolean,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "code")
    var code: String,
    @ColumnInfo(name = "region")
    var region: String,
    @ColumnInfo(name = "subregion")
    var subregion: String,
    @ColumnInfo(name = "currency")
    var currency: String
)