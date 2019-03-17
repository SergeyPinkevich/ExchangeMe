package com.mockingbird.spinkevich.data.repository

import com.mockingbird.spinkevich.data.data.api.service.RestService
import com.mockingbird.spinkevich.data.data.db.dao.CountryDao
import com.mockingbird.spinkevich.data.exceptions.NoSuchElementInDatabaseException
import com.mockingbird.spinkevich.data.mapper.DatabaseMapper
import com.mockingbird.spinkevich.data.utils.JSONHelper
import com.mockingbird.spinkevich.data.utils.Optional
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.repository.CountryRepository
import com.mockingbird.spinkevich.domain.repository.UpdateRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.util.Calendar
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countryDao: CountryDao,
    private val restService: RestService,
    private val jsonHelper: JSONHelper,
    private val updateRepository: UpdateRepository
) : CountryRepository {

    override fun addCountry(country: Country): Completable {
        return Completable.fromCallable {
            countryDao.insert(DatabaseMapper.convertToDatabaseEntity(country, isBase = false, isConverted = false))
        }
    }

    override fun addBaseCountry(country: Country): Completable {
        return Completable.fromCallable {
            countryDao.insert(DatabaseMapper.convertToDatabaseEntity(country, isBase = true, isConverted = false))
        }
    }

    override fun addConvertedCountry(country: Country): Completable {
        return Completable.fromCallable {
            countryDao.insert(DatabaseMapper.convertToDatabaseEntity(country, isBase = false, isConverted = true))
        }
    }

    override fun deleteConvertedCountry(country: Country): Completable {
        return Completable.fromCallable {
            countryDao.delete(DatabaseMapper.convertToDatabaseEntity(country, isBase = false, isConverted = true))
        }
    }

    override fun getBaseCountry(): Single<Country> {
        return Single.fromCallable { Optional(countryDao.getBaseCountry()) }
            .map { baseCountry ->
                baseCountry.value?.let { DatabaseMapper.convertToDomain(it) }
                    ?: throw NoSuchElementInDatabaseException()
            }
    }

    override fun getConvertedCountriesList(): Single<List<Country>> {
        return Single.fromCallable { countryDao.getConvertedCountries() }
            .map { convertedCountries ->
                convertedCountries.map {
                    DatabaseMapper.convertToDomain(it)
                }
            }
    }

    override fun getCountriesListFromDatabase(): Single<List<Country>> {
        return Single.fromCallable { Optional(countryDao.getAllCountries()) }
            .map { optionalList ->
                optionalList.value?.map { DatabaseMapper.convertToDomain(it) }
            }
    }

    override fun getCountriesListFromNetwork(): Single<List<Country>> {
        return restService.getCountriesList()
            .map { json -> jsonHelper.parse(json) }
            .doOnSuccess {
                saveCountriesInDatabase(it).subscribe()
                updateRepository.setLastTimeUpdate(Calendar.getInstance().timeInMillis)
            }
            .onErrorReturn { getCountriesListFromDatabase().blockingGet() }
    }

    private fun saveCountriesInDatabase(countriesList: List<Country>): Completable {
        return Completable.fromCallable {
            countriesList.forEach {
                countryDao.insert(
                    DatabaseMapper.convertToDatabaseEntity(it, isBase = false, isConverted = false)
                )
            }
        }
    }
}