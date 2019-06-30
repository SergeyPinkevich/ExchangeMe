package com.mockingbird.spinkevich.exchangeme.di.module.binder

import com.mockingbird.spinkevich.data.interactor.OnBoardingInteractor
import com.mockingbird.spinkevich.data.repository.OnBoardingRepository
import com.mockingbird.spinkevich.data.repository.impl.OnBoardingRepositoryImpl
import com.mockingbird.spinkevich.domain.usecase.OnBoardingUseCase
import dagger.Binds
import dagger.Module

@Module
interface OnBoardingBinder {

    @Binds
    fun provideOnboardingUseCase(impl: OnBoardingInteractor): OnBoardingUseCase

    @Binds
    fun provideOnboardingRepository(impl: OnBoardingRepositoryImpl): OnBoardingRepository
}