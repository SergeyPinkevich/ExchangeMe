package com.mockingbird.spinkevich.domain.usecase

import com.mockingbird.spinkevich.domain.entity.Country
import io.reactivex.Single

interface CountriesListUseCase {

    fun getAllCountriesList(): Single<List<Country>>
}