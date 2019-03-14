package com.mockingbird.spinkevich.data.mapper

import com.mockingbird.spinkevich.data.data.db.entity.CountryEntity
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.entity.Currency

object DatabaseMapper {

    fun convertToDomain(countryEntity: CountryEntity): Country {
        return Country(
            code = countryEntity.code,
            name = countryEntity.name,
            region = countryEntity.region,
            subRegion = countryEntity.subregion,
            currencies = listOf(Currency(countryEntity.currencyCode, countryEntity.currencyName, countryEntity.currencySymbol))
        )
    }

    fun convertToDatabaseEntity(country: Country, isBase: Boolean, isConverted: Boolean): CountryEntity {
        return CountryEntity(
            isBase = isBase,
            isConverted = isConverted,
            name = country.name,
            code = country.code,
            region = country.region,
            subregion = country.subRegion,
            amount = 10,
            currencyCode = country.currencies.first().code,
            currencyName = country.currencies.first().name,
            currencySymbol = country.currencies.first().symbol
        )
    }
}