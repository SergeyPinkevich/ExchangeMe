package com.mockingbird.spinkevich.domain.usecase

import io.reactivex.Single

interface CurrentRatesUseCase {

    fun getCurrentRatesByCode(currencyCode: String): Single<String>
}