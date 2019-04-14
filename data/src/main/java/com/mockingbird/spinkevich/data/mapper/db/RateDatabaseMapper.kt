package com.mockingbird.spinkevich.data.mapper.db

import com.mockingbird.spinkevich.data.source.db.entity.RateSchema
import com.mockingbird.spinkevich.domain.entity.Rate

object RateDatabaseMapper {

    fun convertRateToDomain(rateSchema: RateSchema): Rate {
        return Rate(
            currency = rateSchema.currency,
            rate = rateSchema.rate
        )
    }

    fun convertRateToDatabaseEntity(rate: Rate): RateSchema {
        return RateSchema(
            id = rate.hashCode(),
            currency = rate.currency,
            rate = rate.rate
        )
    }
}