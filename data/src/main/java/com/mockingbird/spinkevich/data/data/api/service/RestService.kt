package com.mockingbird.spinkevich.data.data.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RestService {

    @GET("daily/{currency}.json")
    fun getCurrentRates(@Path("currency") currency: String): Single<String>

    @GET("countries")
    fun getCountriesList(): Single<String>
}