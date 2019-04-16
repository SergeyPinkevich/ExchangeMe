package com.mockingbird.spinkevich.data.interactor

import com.mockingbird.spinkevich.data.repository.CountryRepository
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.usecase.AllCountriesUseCase
import com.mockingbird.spinkevich.domain.usecase.BaseCountryUseCase
import com.mockingbird.spinkevich.domain.usecase.ConvertedCountriesUseCase
import com.mockingbird.spinkevich.domain.usecase.UpdateUseCase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountryInteractor @Inject constructor(
    private val countryRepository: CountryRepository,
    private val updateUseCase: UpdateUseCase
) : BaseCountryUseCase, AllCountriesUseCase, ConvertedCountriesUseCase {

    override fun addBaseCountry(country: Country): Completable {
        return countryRepository.addBaseCountry(country)
            .subscribeOn(Schedulers.io())
    }

    override fun getBaseCountry(): Single<Country> {
        return countryRepository.getBaseCountry()
            .subscribeOn(Schedulers.io())
    }

    override fun getAllCountriesList(): Single<List<Country>> {
        return if (updateUseCase.isNeedUpdateCountries()) {
            countryRepository.getCountriesListFromNetwork()
        } else {
            countryRepository.getCountriesListFromDatabase()
        }.subscribeOn(Schedulers.io())
    }

    override fun getConvertedCountriesList(): Single<List<Country>> {
        return countryRepository.getConvertedCountriesList()
            .subscribeOn(Schedulers.io())
    }

    override fun addConvertedCountry(country: Country): Completable {
        return countryRepository.addConvertedCountry(country)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteConvertedCountry(country: Country): Completable {
        return countryRepository.deleteConvertedCountry(country)
            .subscribeOn(Schedulers.io())
    }
}