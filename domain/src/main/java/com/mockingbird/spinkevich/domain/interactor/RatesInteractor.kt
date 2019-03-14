package com.mockingbird.spinkevich.domain.interactor

import com.mockingbird.spinkevich.domain.repository.RatesRepository
import com.mockingbird.spinkevich.domain.usecase.RatesUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RatesInteractor @Inject constructor(
    private val ratesRepository: RatesRepository
): RatesUseCase {

    override fun getCurrentRatesByCode(currency: String): Single<String> {
        return ratesRepository.getCurrentRates(currency)
            .subscribeOn(Schedulers.io())
    }
}