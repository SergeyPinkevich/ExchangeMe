package com.mockingbird.spinkevich.domain.entity

data class Rate(
    val currency: String,
    val rate: Float
)

data class RateResponse(
    val rates: List<Rate>,
    val source: Source
)

enum class Source {
    DATABASE,
    NETWORK
}