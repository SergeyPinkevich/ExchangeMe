package com.mockingbird.spinkevich.exchangeme.di.module

import dagger.Module

@Module(includes = [NewCurrencyBinder::class, ApplicationModule::class])
class NewCurrencyModule {
}