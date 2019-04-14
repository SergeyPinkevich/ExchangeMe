package com.mockingbird.spinkevich.exchangeme.di.module.binder

import com.mockingbird.spinkevich.data.interactor.RatesInteractor
import com.mockingbird.spinkevich.data.repository.RatesRepository
import com.mockingbird.spinkevich.data.repository.impl.RatesRepositoryImpl
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