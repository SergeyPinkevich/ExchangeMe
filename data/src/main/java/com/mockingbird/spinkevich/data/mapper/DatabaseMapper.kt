package com.mockingbird.spinkevich.data.mapper

import com.mockingbird.spinkevich.data.data.db.entity.CountryEntity
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.entity.Currency

object DatabaseMapper {

    fun convertToDomain(countryEntity: CountryEntity): Country {
        return Country(
            countryEntity.code,
            countryEntity.name,
            countryEntity.region,
            countryEntity.subregion,
            listOf(Currency(countryEntity.currencyCode, countryEntity.currencyName, countryEntity.currencySymbol))
        )
    }

    fun convertToDatabaseEntity(country: Country): CountryEntity {
        return CountryEntity(
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