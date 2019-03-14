package com.mockingbird.spinkevich.domain.repository

import com.mockingbird.spinkevich.domain.entity.Country
import io.reactivex.Completable
import io.reactivex.Single

interface CountryRepository {

    fun addCountry(country: Country): Completable

    fun addBaseCountry(country: Country): Completable

    fun addConvertedCountry(country: Country): Completable

    fun deleteConvertedCountry(country: Country): Completable

    fun getCountriesList(): Single<List<Country>>

    fun getBaseCountry(): Single<Country>

    fun getConvertedCountriesList(): Single<List<Country>>
}