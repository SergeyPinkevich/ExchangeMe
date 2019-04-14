package com.mockingbird.spinkevich.data.mapper.db.typeconverter

import android.arch.persistence.room.TypeConverter

object FloatTypeConverter {

    @TypeConverter
    fun toFloat(value: String): Float = value.toFloat()

    @TypeConverter
    fun fromFloat(value: Float): String = value.toString()
}