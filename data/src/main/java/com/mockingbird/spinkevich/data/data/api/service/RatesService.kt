package com.mockingbird.spinkevich.data.data.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RatesService {

    @GET("daily/{currency}.json")
    fun getCurrentRates(@Path("currency") currency: String): Single<String>
}