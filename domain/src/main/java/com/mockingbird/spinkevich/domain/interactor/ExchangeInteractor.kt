package com.mockingbird.spinkevich.domain.interactor

import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.repository.ExchangeRepository
import com.mockingbird.spinkevich.domain.usecase.ConvertedCountriesListUseCase
import com.mockingbird.spinkevich.domain.usecase.CurrentRatesUseCase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExchangeInteractor @Inject constructor(
    private val exchangeRepository: ExchangeRepository
): CurrentRatesUseCase, ConvertedCountriesListUseCase {

    override fun getCurrentRatesByCode(currency: String): Single<String> {
        return exchangeRepository.getCurrentRates(currency)
            .subscribeOn(Schedulers.io())
    }

    override fun getConvertedCountriesList(): Single<List<Country>> {
        return exchangeRepository.getConvertedCountriesList()
            .subscribeOn(Schedulers.io())
    }

    override fun addConvertedCountry(country: Country): Completable {
        return exchangeRepository.addConvertedCountry(country)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteConvertedCountry(country: Country) {
        exchangeRepository.deleteConvertedCountry(country)
    }
}