package com.mockingbird.spinkevich.data.repository

import com.mockingbird.spinkevich.domain.entity.Country
import io.reactivex.Completable
import io.reactivex.Single

interface CountryRepository {

    fun addBaseCountry(country: Country): Completable

    fun addConvertedCountry(country: Country): Completable

    fun deleteConvertedCountry(country: Country): Completable

    fun swapCountries(baseCountry: Country, swappedCountry: Country): Completable

    fun getCountriesListFromNetwork(): Single<List<Country>>

    fun getCountriesListFromDatabase(): Single<List<Country>>

    fun getBaseCountry(): Single<Country>

    fun getConvertedCountriesList(): Single<List<Country>>
}