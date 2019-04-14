package com.mockingbird.spinkevich.exchangeme.di.module.binder

import com.mockingbird.spinkevich.data.interactor.UpdateInteractor
import com.mockingbird.spinkevich.data.repository.UpdateRepository
import com.mockingbird.spinkevich.data.repository.impl.UpdateRepositoryImpl
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