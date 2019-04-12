package com.mockingbird.spinkevich.data.data.api.service

import io.reactivex.Single
import retrofit2.http.GET

interface RestService {

    @GET("rates")
    fun getCurrentRates(): Single<String>

    @GET("countries")
    fun getCountriesList(): Single<String>
}