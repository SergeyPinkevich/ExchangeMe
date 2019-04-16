package com.mockingbird.spinkevich.domain.usecase

interface UpdateUseCase {

    fun isNeedUpdateCountries(): Boolean

    fun isNeedUpdateRates(): Boolean
}