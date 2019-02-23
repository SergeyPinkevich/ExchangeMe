package com.mockingbird.spinkevich.data.repository

import com.mockingbird.spinkevich.data.data.api.service.RatesService
import com.mockingbird.spinkevich.domain.repository.RatesRepository
import io.reactivex.Single
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val service: RatesService
) : RatesRepository {

    override fun getCurrentRates(currency: String): Single<String> {
        return service.getCurrentRates(currency)
    }
}