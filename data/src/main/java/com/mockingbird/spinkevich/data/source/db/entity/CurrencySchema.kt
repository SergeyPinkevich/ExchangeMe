package com.mockingbird.spinkevich.data.source.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencySchema(
    @PrimaryKey
    var id: String,
    @ColumnInfo(name = "code")
    var code: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "symbol")
    var symbol: String
)