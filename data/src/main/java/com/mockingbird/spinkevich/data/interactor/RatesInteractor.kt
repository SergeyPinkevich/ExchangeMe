package com.mockingbird.spinkevich.data.interactor

import com.mockingbird.spinkevich.data.repository.RatesRepository
import com.mockingbird.spinkevich.domain.entity.RateResponse
import com.mockingbird.spinkevich.domain.entity.Source
import com.mockingbird.spinkevich.domain.usecase.RatesUseCase
import com.mockingbird.spinkevich.domain.usecase.UpdateUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RatesInteractor @Inject constructor(
    private val ratesRepository: RatesRepository,
    private val updateUseCase: UpdateUseCase
): RatesUseCase {

    override fun getCurrentRates(): Single<RateResponse> {
        return if (updateUseCase.isNeedUpdateRates()) {
            ratesRepository.getCurrentRatesFromNetwork()
                .flatMap { Single.fromCallable { RateResponse(it, Source.NETWORK) } }
        } else {
            ratesRepository.getCurrentRatesFromDatabase()
                .flatMap { Single.fromCallable { RateResponse(it, Source.DATABASE) } }
        }.subscribeOn(Schedulers.io())
    }
}