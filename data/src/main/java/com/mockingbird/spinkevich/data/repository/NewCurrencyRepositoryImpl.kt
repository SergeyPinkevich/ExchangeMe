package com.mockingbird.spinkevich.data.repository

import com.mockingbird.spinkevich.data.utils.DataHelper
import com.mockingbird.spinkevich.domain.entity.model.Country
import com.mockingbird.spinkevich.domain.repository.NewCurrencyRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import mapper.NewCurrencyMapper
import javax.inject.Inject

class NewCurrencyRepositoryImpl @Inject constructor(
    private val dataHelper: DataHelper
) : NewCurrencyRepository {

    override fun getCountriesList(): Single<List<Country>> {
        return Single.fromCallable { dataHelper.getCurrencyLocalInfo()?.list }
            .map {
                it.map {
                    NewCurrencyMapper.convertToDomain(it)
                }
            }
            .subscribeOn(Schedulers.computation())
    }
}