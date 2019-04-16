package com.mockingbird.spinkevich.data.interactor

import com.mockingbird.spinkevich.data.repository.RatesRepository
import com.mockingbird.spinkevich.domain.entity.Rate
import com.mockingbird.spinkevich.domain.usecase.RatesUseCase
import com.mockingbird.spinkevich.domain.usecase.UpdateUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RatesInteractor @Inject constructor(
    private val ratesRepository: RatesRepository,
    private val updateUseCase: UpdateUseCase
): RatesUseCase {

    override fun getCurrentRates(): Single<List<Rate>> {
        return if (updateUseCase.isNeedUpdateRates()) {
            ratesRepository.getCurrentRatesFromNetwork()
        } else {
            ratesRepository.getCurrentRatesFromDatabase()
        }.subscribeOn(Schedulers.io())
    }
}