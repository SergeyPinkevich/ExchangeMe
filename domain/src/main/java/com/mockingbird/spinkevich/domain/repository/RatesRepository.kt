package com.mockingbird.spinkevich.domain.repository

import io.reactivex.Single

interface RatesRepository {

    fun getCurrentRates(): Single<String>
}