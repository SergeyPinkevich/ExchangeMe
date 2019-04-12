package com.mockingbird.spinkevich.data.repository

import com.mockingbird.spinkevich.data.data.api.service.RestService
import com.mockingbird.spinkevich.data.data.db.dao.CountryDao
import com.mockingbird.spinkevich.domain.repository.RatesRepository
import io.reactivex.Single
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val service: RestService,
    private val countryDao: CountryDao
) : RatesRepository {

    override fun getCurrentRates(): Single<String> {
        return service.getCurrentRates()
    }
}