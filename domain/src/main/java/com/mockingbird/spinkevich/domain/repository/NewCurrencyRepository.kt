package com.mockingbird.spinkevich.domain.repository

import com.mockingbird.spinkevich.domain.entity.Country
import io.reactivex.Single

interface NewCurrencyRepository {

    fun getCountriesList(): Single<List<Country>>
}