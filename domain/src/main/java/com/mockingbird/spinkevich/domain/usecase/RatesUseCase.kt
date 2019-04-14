package com.mockingbird.spinkevich.domain.usecase

import com.mockingbird.spinkevich.domain.entity.Rate
import io.reactivex.Single

interface RatesUseCase {

    fun getCurrentRates(): Single<List<Rate>>
}