package com.mockingbird.spinkevich.data.repository.impl

import com.mockingbird.spinkevich.data.repository.RatesRepository
import com.mockingbird.spinkevich.data.source.api.service.RestService
import com.mockingbird.spinkevich.data.source.db.dao.CountryDao
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