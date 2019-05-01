package com.mockingbird.spinkevich.domain.usecase

import com.mockingbird.spinkevich.domain.entity.Country
import io.reactivex.Completable

interface SwapCountriesUseCase {

    fun swapCountries(baseCountry: Country, swappedCountry: Country): Completable
}