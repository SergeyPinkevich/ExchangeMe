package com.mockingbird.spinkevich.exchangeme.di.module.binder

import com.mockingbird.spinkevich.data.interactor.CountryInteractor
import com.mockingbird.spinkevich.data.repository.CountryRepository
import com.mockingbird.spinkevich.data.repository.impl.CountryRepositoryImpl
import com.mockingbird.spinkevich.domain.usecase.AllCountriesUseCase
import com.mockingbird.spinkevich.domain.usecase.BaseCountryUseCase
import com.mockingbird.spinkevich.domain.usecase.ConvertedCountriesUseCase
import com.mockingbird.spinkevich.domain.usecase.SwapCountriesUseCase
import dagger.Binds
import dagger.Module

@Module
interface CountryBinder {

    @Binds
    fun provideBaseCountryUseCase(interactor: CountryInteractor): BaseCountryUseCase

    @Binds
    fun provideAllCountriesListUseCase(interactor: CountryInteractor): AllCountriesUseCase

    @Binds
    fun provideConvertedCountriesUseCase(interactor: CountryInteractor): ConvertedCountriesUseCase

    @Binds
    fun provideSwapCountriesUseCase(interactor: CountryInteractor): SwapCountriesUseCase

    @Binds
    fun provideRepository(repo: CountryRepositoryImpl): CountryRepository
}