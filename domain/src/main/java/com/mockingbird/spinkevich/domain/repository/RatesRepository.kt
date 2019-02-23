package com.mockingbird.spinkevich.domain.repository

import io.reactivex.Single

interface RatesRepository {

    fun getCurrentRates(currency: String): Single<String>
}