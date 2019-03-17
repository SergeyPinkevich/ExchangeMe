package com.mockingbird.spinkevich.exchangeme.di.module.binder

import com.mockingbird.spinkevich.data.repository.UpdateRepositoryImpl
import com.mockingbird.spinkevich.domain.interactor.UpdateInteractor
import com.mockingbird.spinkevich.domain.repository.UpdateRepository
import com.mockingbird.spinkevich.domain.usecase.UpdateUseCase
import dagger.Binds
import dagger.Module

@Module
interface UpdateBinder {

    @Binds
    fun provideUpdateUseCase(interactor: UpdateInteractor): UpdateUseCase

    @Binds
    fun provideRepository(repo: UpdateRepositoryImpl): UpdateRepository
}