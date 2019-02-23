package com.mockingbird.spinkevich.data.entity

import com.google.gson.annotations.SerializedName

data class Rate(
    @SerializedName("code")
    val code: String,
    @SerializedName("rate")
    val rate: Float,
    @SerializedName("inverseRate")
    val inverseRate: Float
)