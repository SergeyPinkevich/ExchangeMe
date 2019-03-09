package com.mockingbird.spinkevich.data.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(
    tableName = "currencies",
    foreignKeys = [
        ForeignKey(
            entity = CountryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("countryId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    @ColumnInfo(name = "code")
    var code: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "symbol")
    var symbol: String,
    @ColumnInfo(name = "countryId")
    var countryId: Long
)