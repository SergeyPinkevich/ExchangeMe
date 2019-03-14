package com.mockingbird.spinkevich.exchangeme.di.module.binder

import com.mockingbird.spinkevich.data.repository.RatesRepositoryImpl
import com.mockingbird.spinkevich.domain.interactor.RatesInteractor
import com.mockingbird.spinkevich.domain.repository.RatesRepository
import com.mockingbird.spinkevich.domain.usecase.RatesUseCase
import dagger.Binds
import dagger.Module

@Module
interface RatesBinder {

    @Binds
    fun provideRatesUseCase(interactor: RatesInteractor): RatesUseCase

    @Binds
    fun provideRepository(repo: RatesRepositoryImpl): RatesRepository
}