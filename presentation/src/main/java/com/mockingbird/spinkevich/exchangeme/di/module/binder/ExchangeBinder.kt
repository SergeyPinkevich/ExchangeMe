package com.mockingbird.spinkevich.exchangeme.di.module.binder

import com.mockingbird.spinkevich.data.repository.RatesRepositoryImpl
import com.mockingbird.spinkevich.data.utils.permission.PermissionManager
import com.mockingbird.spinkevich.data.utils.permission.PermissionManagerImpl
import com.mockingbird.spinkevich.domain.interactor.RatesInteractor
import com.mockingbird.spinkevich.domain.repository.RatesRepository
import com.mockingbird.spinkevich.domain.usecase.CurrentRatesUseCase
import dagger.Binds
import dagger.Module

@Module
interface ExchangeBinder {

    @Binds
    fun providePermissionManager(manager: PermissionManagerImpl): PermissionManager

    @Binds
    fun provideCurrentRatesUseCase(interactor: RatesInteractor): CurrentRatesUseCase

    @Binds
    fun provideRepository(repo: RatesRepositoryImpl): RatesRepository
}