package com.mockingbird.spinkevich.data.repository

import com.mockingbird.spinkevich.data.data.api.service.RestService
import com.mockingbird.spinkevich.data.data.db.dao.CountryDao
import com.mockingbird.spinkevich.data.data.db.dao.CurrencyDao
import com.mockingbird.spinkevich.data.data.db.entity.CurrencySchema
import com.mockingbird.spinkevich.data.mapper.DatabaseMapper
import com.mockingbird.spinkevich.data.utils.JSONHelper
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.repository.CountryRepository
import com.mockingbird.spinkevich.domain.repository.UpdateRepository
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

    override fun addCountry(country: Country): Completable {
        return Completable.fromCallable {
            currencyDao.insert(DatabaseMapper.convertCurrencyToDatabaseEntity(country.currencies.first()))
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
            countryDao.update(DatabaseMapper.convertToDatabaseEntity(country, isBase = false, isConverted = false))
        }
    }

    override fun getBaseCountry(): Single<Country> {
        return Single.fromCallable { countryDao.getBaseCountry() }
            .flatMap { baseCountry ->
                return@flatMap Single.fromCallable { currencyDao.getCurrency(baseCountry.currency) }
                    .map { return@map Pair(baseCountry, it) }
            }
            .map { DatabaseMapper.convertCountryToDomain(it.first, it.second) }
    }

    override fun getConvertedCountriesList(): Single<List<Country>> {
        return Single.fromCallable { countryDao.getConvertedCountries() }
            .flatMap { convertedCountries ->
                return@flatMap Single.fromCallable {
                    val currenciesList = mutableListOf<CurrencySchema>()
                    convertedCountries.forEach {
                        currenciesList.add(currencyDao.getCurrency(it.currency))
                    }
                    return@fromCallable currenciesList
                }.map { return@map Pair(convertedCountries, it) }
            }
            .map { (countryList, currencyList) ->
                val countriesDomain = mutableListOf<Country>()
                for (i in 0 until countryList.size) {
                    countriesDomain.add(
                        DatabaseMapper.convertCountryToDomain(countryList[i], currencyList[i])
                    )
                }
                countriesDomain
            }
    }

    override fun getCountriesListFromDatabase(): Single<List<Country>> {
        return Single.fromCallable { countryDao.getAllCountries() }
            .flatMap { allCountries ->
                return@flatMap Single.fromCallable {
                    val currenciesList = mutableListOf<CurrencySchema>()
                    allCountries.forEach {
                        currenciesList.add(currencyDao.getCurrency(it.currency))
                    }
                    return@fromCallable currenciesList
                }.map { return@map Pair(allCountries, it) }
            }
            .map { (countryList, currencyList) ->
                val countriesDomain = mutableListOf<Country>()
                for (i in 0 until countryList.size) {
                    countriesDomain.add(
                        DatabaseMapper.convertCountryToDomain(countryList[i], currencyList[i])
                    )
                }
                countriesDomain
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
                currencyDao.insert(DatabaseMapper.convertCurrencyToDatabaseEntity(it.currencies.first()))
                countryDao.insert(DatabaseMapper.convertToDatabaseEntity(it, isBase = false, isConverted = false))
            }
        }
    }
}