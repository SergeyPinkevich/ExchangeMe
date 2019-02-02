package com.mockingbird.spinkevich.domain.repository

import com.mockingbird.spinkevich.domain.entity.model.Country
import io.reactivex.Single

interface NewCurrencyRepository {

    fun getCountriesList(): Single<List<Country>>
}