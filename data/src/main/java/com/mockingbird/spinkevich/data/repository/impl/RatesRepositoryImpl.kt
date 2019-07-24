package com.mockingbird.spinkevich.data.repository.impl

import com.mockingbird.spinkevich.data.mapper.db.RateDatabaseMapper
import com.mockingbird.spinkevich.data.repository.RatesRepository
import com.mockingbird.spinkevich.data.repository.UpdateRepository
import com.mockingbird.spinkevich.data.source.api.service.RestService
import com.mockingbird.spinkevich.data.source.db.dao.RateDao
import com.mockingbird.spinkevich.data.utils.JSONHelper
import com.mockingbird.spinkevich.domain.entity.Rate
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.Calendar
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val service: RestService,
    private val jsonHelper: JSONHelper,
    private val rateDao: RateDao,
    private val updateRepository: UpdateRepository
) : RatesRepository {

    override fun getCurrentRatesFromNetwork(): Single<List<Rate>> {
        return service.getCurrentRates()
            .map { json -> jsonHelper.parseRates(json) }
            .doOnSuccess {
                saveRatesInDatabase(it).subscribe()
                updateRepository.setLastTimeUpdateRates(Calendar.getInstance().timeInMillis)
            }
            .onErrorResumeNext { getCurrentRatesFromDatabase() }
            .subscribeOn(Schedulers.io())
    }

    override fun getCurrentRatesFromDatabase(): Single<List<Rate>> {
        return Single.fromCallable { rateDao.getRates() }
            .map {
                val ratesDomain = mutableListOf<Rate>()
                it.forEach { ratesDomain.add(RateDatabaseMapper.convertRateToDomain(it)) }
                ratesDomain
            }
    }

    private fun saveRatesInDatabase(rates: List<Rate>): Completable {
        return Completable.fromCallable {
            rates.forEach {
                rateDao.insert(RateDatabaseMapper.convertRateToDatabaseEntity(it))
            }
        }
    }
}