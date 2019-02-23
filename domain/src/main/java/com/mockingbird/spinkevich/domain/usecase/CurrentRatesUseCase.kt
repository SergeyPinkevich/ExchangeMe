package com.mockingbird.spinkevich.domain.usecase

import io.reactivex.Single

interface CurrentRatesUseCase {

    fun getCurrentRates(currency: String): Single<String>
}