package com.mockingbird.spinkevich.domain.usecase

import com.mockingbird.spinkevich.domain.entity.Country
import io.reactivex.Completable
import io.reactivex.Single

interface BaseCountryUseCase {

    fun addBaseCountry(country: Country): Completable

    fun getBaseCountry(): Single<Country>
}