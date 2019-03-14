package com.mockingbird.spinkevich.exchangeme.di.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mockingbird.spinkevich.data.data.api.service.RestService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://exchangeme-api-heroku.herokuapp.com/"

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRateService(retrofit: Retrofit): RestService {
        return retrofit.create(RestService::class.java)
    }
}