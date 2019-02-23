package com.mockingbird.spinkevich.exchangeme.di.module

import dagger.Module

@Module(includes = [ExchangeBinder::class, ApplicationModule::class])
class ExchangeModule {
}