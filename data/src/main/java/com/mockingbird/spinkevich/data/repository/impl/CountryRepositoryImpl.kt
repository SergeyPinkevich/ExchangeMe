package com.mockingbird.spinkevich.data.repository.impl

import com.mockingbird.spinkevich.data.mapper.db.CountryDatabaseMapper
import com.mockingbird.spinkevich.data.repository.CountryRepository
import com.mockingbird.spinkevich.data.repository.UpdateRepository
import com.mockingbird.spinkevich.data.source.api.service.RestService
import com.mockingbird.spinkevich.data.source.db.dao.CountryDao
import com.mockingbird.spinkevich.data.source.db.dao.CurrencyDao
import com.mockingbird.spinkevich.data.utils.JSONHelper
import com.mockingbird.spinkevich.domain.entity.Country
import io.reactivex.Completable
import io.reactivex.Single
import java.util.Calendar
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countryDao: CountryDao,
    private val currencyDao: CurrencyDao,
    private val restService: RestService,
    private val jsonHelper: JSONHelper,
    private val updateRepository: UpdateRepository
) : CountryRepository {

    override fun addBaseCountry(country: Country): Completable {
        return Completable.fromCallable {
            countryDao.update(
                CountryDatabaseMapper.convertToDatabaseEntity(
                    country,
                    isBase = true,
                    isConverted = false
                )
            )
        }
    }

    override fun addConvertedCountry(country: Country): Completable {
        return Completable.fromCallable {
            countryDao.update(
                CountryDatabaseMapper.convertToDatabaseEntity(
                    country,
                    isBase = false,
                    isConverted = true
                )
            )
        }
    }

    override fun deleteConvertedCountry(country: Country): Completable {
        return Completable.fromCallable {
            countryDao.update(
                CountryDatabaseMapper.convertToDatabaseEntity(
                    country,
                    isBase = false,
                    isConverted = false
                )
            )
        }
    }

    override fun swapCountries(baseCountry: Country, swappedCountry: Country): Completable {
        return Completable.fromCallable {
            countryDao.update(
                CountryDatabaseMapper.convertToDatabaseEntity(
                    swappedCountry,
                    isBase = true,
                    isConverted = false
                )
            )
            countryDao.update(
                CountryDatabaseMapper.convertToDatabaseEntity(
                    baseCountry,
                    isBase = false,
                    isConverted = true
                )
            )
        }
    }

    override fun getBaseCountry(): Single<Country> {
        return Single.fromCallable { countryDao.getBaseCountry() }
            .map { baseCountrySchema ->
                val currencySchema = currencyDao.getCurrency(baseCountrySchema.currency)
                CountryDatabaseMapper.convertCountryToDomain(baseCountrySchema, currencySchema)
            }
    }

    override fun getConvertedCountriesList(): Single<List<Country>> {
        return Single.fromCallable { countryDao.getConvertedCountries() }
            .map { convertedCountries ->
                convertedCountries
                    .map { countrySchema ->
                        val currencySchema = currencyDao.getCurrency(countrySchema.currency)
                        CountryDatabaseMapper.convertCountryToDomain(countrySchema, currencySchema)
                }
            }
    }

    override fun getCountriesListFromDatabase(): Single<List<Country>> {
        return Single.fromCallable { countryDao.getAllCountries() }
            .map { allCountries ->
                allCountries
                    .map { countrySchema ->
                        val currency = currencyDao.getCurrency(countrySchema.currency)
                        CountryDatabaseMapper.convertCountryToDomain(countrySchema, currency)
                    }
            }
    }

    override fun getCountriesListFromNetwork(): Single<List<Country>> {
        return restService.getCountriesList()
            .map { json -> jsonHelper.parseCountries(json) }
            .flatMap {
                return@flatMap saveCountriesInDatabase(it).andThen(Single.just(it))
            }
            .onErrorResumeNext { getCountriesListFromDatabase() }
    }

    private fun saveCountriesInDatabase(countriesList: List<Country>): Completable {
        return Completable.fromCallable {
            countriesList.forEach {
                currencyDao.insert(CountryDatabaseMapper.convertCurrencyToDatabaseEntity(it.currency))
                countryDao.insert(
                    CountryDatabaseMapper.convertToDatabaseEntity(
                        it,
                        isBase = false,
                        isConverted = false
                    )
                )
            }
            updateRepository.setLastTimeUpdateCountries(Calendar.getInstance().timeInMillis)
        }
    }
}