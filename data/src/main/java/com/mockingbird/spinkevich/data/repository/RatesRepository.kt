package com.mockingbird.spinkevich.data.repository

import io.reactivex.Single

interface RatesRepository {

    fun getCurrentRates(): Single<String>
}