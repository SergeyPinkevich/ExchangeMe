package com.mockingbird.spinkevich.data.repository

import com.mockingbird.spinkevich.domain.entity.Rate
import io.reactivex.Single

interface RatesRepository {

    fun getCurrentRatesFromNetwork(): Single<List<Rate>>

    fun getCurrentRatesFromDatabase(): Single<List<Rate>>
}