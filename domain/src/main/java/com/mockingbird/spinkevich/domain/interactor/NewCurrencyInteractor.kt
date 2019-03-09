package com.mockingbird.spinkevich.domain.interactor

import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.repository.NewCurrencyRepository
import com.mockingbird.spinkevich.domain.usecase.CountriesListUseCase
import io.reactivex.Single
import javax.inject.Inject

class NewCurrencyInteractor @Inject constructor(
    private val repository: NewCurrencyRepository
) : CountriesListUseCase {

    override fun getAllCountriesList(): Single<List<Country>> = repository.getCountriesList()
}