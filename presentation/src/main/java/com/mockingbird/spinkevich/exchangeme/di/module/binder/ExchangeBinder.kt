package com.mockingbird.spinkevich.exchangeme.di.module.binder

import com.mockingbird.spinkevich.data.repository.ExchangeRepositoryImpl
import com.mockingbird.spinkevich.data.utils.permission.PermissionManager
import com.mockingbird.spinkevich.data.utils.permission.PermissionManagerImpl
import com.mockingbird.spinkevich.domain.interactor.ExchangeInteractor
import com.mockingbird.spinkevich.domain.repository.ExchangeRepository
import com.mockingbird.spinkevich.domain.usecase.ConvertedCountriesListUseCase
import com.mockingbird.spinkevich.domain.usecase.CurrentRatesUseCase
import dagger.Binds
import dagger.Module

@Module
interface ExchangeBinder {

    @Binds
    fun providePermissionManager(manager: PermissionManagerImpl): PermissionManager

    @Binds
    fun provideCurrentRatesUseCase(interactor: ExchangeInteractor): CurrentRatesUseCase

    @Binds
    fun provideConvertedCountriesListUseCase(interactor: ExchangeInteractor): ConvertedCountriesListUseCase

    @Binds
    fun provideRepository(repo: ExchangeRepositoryImpl): ExchangeRepository
}