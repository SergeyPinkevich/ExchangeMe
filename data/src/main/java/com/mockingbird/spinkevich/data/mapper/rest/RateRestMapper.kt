package com.mockingbird.spinkevich.data.mapper.rest

import com.mockingbird.spinkevich.data.entity.Rate as RateData
import com.mockingbird.spinkevich.domain.entity.Rate as RateDomain

object RateRestMapper {

    fun convertToDomain(rate: RateData): RateDomain {
        return RateDomain(
            rate.currency ?: "",
            rate.rate?.toFloat() ?: 0.0F
        )
    }
}