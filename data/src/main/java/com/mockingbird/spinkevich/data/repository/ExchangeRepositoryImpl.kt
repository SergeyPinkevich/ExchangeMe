package com.mockingbird.spinkevich.data.repository

import com.mockingbird.spinkevich.data.data.api.service.RatesService
import com.mockingbird.spinkevich.data.data.db.dao.ExchangeDao
import com.mockingbird.spinkevich.data.mapper.DatabaseMapper
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.repository.ExchangeRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ExchangeRepositoryImpl @Inject constructor(
    private val service: RatesService,
    private val exchangeDao: ExchangeDao
) : ExchangeRepository {

    override fun getCurrentRates(currency: String): Single<String> {
        return service.getCurrentRates(currency)
    }

    override fun getConvertedCountriesList(): Single<List<Country>> {
        return Single.fromCallable { exchangeDao.getAll() }
            .map {
                it.map {
                    DatabaseMapper.convertToDomain(it)
                }
            }
    }

    override fun addConvertedCountry(country: Country): Completable {
        return Completable.fromCallable {
            exchangeDao.insert(DatabaseMapper.convertToDatabaseEntity(country))
        }
    }

    override fun deleteConvertedCountry(country: Country) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}