package com.mockingbird.spinkevich.domain.usecase

import com.mockingbird.spinkevich.domain.entity.RateResponse
import io.reactivex.Single

interface RatesUseCase {

    fun getCurrentRates(): Single<RateResponse>
}