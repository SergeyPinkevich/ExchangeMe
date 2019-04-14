package com.mockingbird.spinkevich.data.mapper.rest

import com.mockingbird.spinkevich.data.entity.Country as CountryData
import com.mockingbird.spinkevich.data.entity.Currency as CurrencyData
import com.mockingbird.spinkevich.domain.entity.Country as CountryDomain
import com.mockingbird.spinkevich.domain.entity.Currency as CurrencyDomain

object CountryRestMapper {

    fun convertToDomain(country: CountryData): CountryDomain {
        return CountryDomain(
            country.alpha2Code ?: "",
            country.name ?: "",
            country.region ?: "",
            country.subregion ?: "",
            country.currencies?.map { convertCurrencyToDomain(it) } ?: emptyList()
        )
    }

    private fun convertCurrencyToDomain(currency: CurrencyData): CurrencyDomain {
        return CurrencyDomain(
            currency.code ?: "",
            currency.name ?: "",
            currency.symbol ?: ""
        )
    }
}