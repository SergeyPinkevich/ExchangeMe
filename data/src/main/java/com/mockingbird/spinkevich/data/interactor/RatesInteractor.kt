package com.mockingbird.spinkevich.data.interactor

import com.mockingbird.spinkevich.data.repository.RatesRepository
import com.mockingbird.spinkevich.domain.entity.RateResponse
import com.mockingbird.spinkevich.domain.entity.Source
import com.mockingbird.spinkevich.domain.usecase.RatesUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RatesInteractor @Inject constructor(
    private val ratesRepository: RatesRepository
): RatesUseCase {

    override fun getCurrentRatesFromNetwork(): Single<RateResponse> {
        return ratesRepository.getCurrentRatesFromNetwork()
            .flatMap { Single.fromCallable { RateResponse(it, Source.NETWORK) } }
            .subscribeOn(Schedulers.io())
    }

    override fun getCurrentRatesFromDatabase(): Single<RateResponse> {
        return ratesRepository.getCurrentRatesFromDatabase()
            .flatMap { Single.fromCallable { RateResponse(it, Source.DATABASE) } }
            .subscribeOn(Schedulers.io())
    }
}