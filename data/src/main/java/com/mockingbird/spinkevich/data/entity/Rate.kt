package com.mockingbird.spinkevich.data.entity

import com.google.gson.annotations.SerializedName

data class RateResponse(
    @SerializedName("rates")
    val rates: List<Rate>
)

data class Rate(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("rate")
    val rate: String?
)