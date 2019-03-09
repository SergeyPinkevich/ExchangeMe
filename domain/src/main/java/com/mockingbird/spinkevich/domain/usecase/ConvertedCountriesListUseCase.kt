package com.mockingbird.spinkevich.domain.usecase

import com.mockingbird.spinkevich.domain.entity.Country
import io.reactivex.Completable
import io.reactivex.Single

interface ConvertedCountriesListUseCase {

    fun getConvertedCountriesList(): Single<List<Country>>

    fun addConvertedCountry(country: Country): Completable

    fun deleteConvertedCountry(country: Country)
}