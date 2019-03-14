package com.mockingbird.spinkevich.exchangeme.di.module.binder

import com.mockingbird.spinkevich.data.repository.CountryRepositoryImpl
import com.mockingbird.spinkevich.domain.interactor.CountryInteractor
import com.mockingbird.spinkevich.domain.repository.CountryRepository
import com.mockingbird.spinkevich.domain.usecase.AllCountriesUseCase
import com.mockingbird.spinkevich.domain.usecase.BaseCountryUseCase
import com.mockingbird.spinkevich.domain.usecase.ConvertedCountriesUseCase
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
    fun provideRepository(repo: CountryRepositoryImpl): CountryRepository
}