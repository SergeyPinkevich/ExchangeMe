package com.mockingbird.spinkevich.domain.interactor

import com.mockingbird.spinkevich.domain.repository.RatesRepository
import com.mockingbird.spinkevich.domain.usecase.CurrentRatesUseCase
import io.reactivex.Single
import javax.inject.Inject

class RatesInteractor @Inject constructor(
    private val ratesRepository: RatesRepository
): CurrentRatesUseCase {

    override fun getCurrentRates(currency: String): Single<String> {
        return ratesRepository.getCurrentRates(currency)
    }
}