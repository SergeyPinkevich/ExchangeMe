package com.mockingbird.spinkevich.domain.usecase

import io.reactivex.Single

interface RatesUseCase {

    fun getCurrentRatesByCode(currencyCode: String): Single<String>
}