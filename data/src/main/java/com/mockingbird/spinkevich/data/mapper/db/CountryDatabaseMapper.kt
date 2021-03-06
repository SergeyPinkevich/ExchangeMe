package com.mockingbird.spinkevich.data.mapper.db

import com.mockingbird.spinkevich.data.source.db.entity.CountrySchema
import com.mockingbird.spinkevich.data.source.db.entity.CurrencySchema
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.domain.entity.Currency

object CountryDatabaseMapper {

    fun convertCountryToDomain(countrySchema: CountrySchema, currency: CurrencySchema): Country {
        return Country(
            code = countrySchema.code,
            name = countrySchema.name,
            region = countrySchema.region,
            subRegion = countrySchema.subregion,
            currency = convertCurrencyToDomain(currency),
            englishName = countrySchema.englishName
        )
    }

    fun convertToDatabaseEntity(country: Country, isBase: Boolean, isConverted: Boolean): CountrySchema {
        return CountrySchema(
            id = country.code.hashCode(),
            isBase = isBase,
            isConverted = isConverted,
            name = country.name,
            code = country.code,
            region = country.region,
            subregion = country.subRegion,
            currency = country.currency.code,
            englishName = country.englishName
        )
    }

    fun convertCurrencyToDatabaseEntity(currency: Currency): CurrencySchema {
        return CurrencySchema(
            id = currency.code,
            code = currency.code,
            name = currency.name,
            symbol = currency.symbol
        )
    }

    private fun convertCurrencyToDomain(currencySchema: CurrencySchema): Currency {
        return Currency(
            code = currencySchema.code,
            name = currencySchema.name,
            symbol = currencySchema.symbol,
            amount = 0.0F
        )
    }
}