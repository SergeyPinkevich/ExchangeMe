package com.mockingbird.spinkevich.exchangeme.di.module

import com.mockingbird.spinkevich.data.repository.NewCurrencyRepositoryImpl
import com.mockingbird.spinkevich.domain.interactor.NewCurrencyInteractor
import com.mockingbird.spinkevich.domain.repository.NewCurrencyRepository
import com.mockingbird.spinkevich.domain.usecase.CountriesListUseCase
import dagger.Binds
import dagger.Module

@Module
interface NewCurrencyBinder {

    @Binds
    fun provideCountriesListUseCase(interactor: NewCurrencyInteractor): CountriesListUseCase

    @Binds
    fun provideRepository(repo: NewCurrencyRepositoryImpl): NewCurrencyRepository
}