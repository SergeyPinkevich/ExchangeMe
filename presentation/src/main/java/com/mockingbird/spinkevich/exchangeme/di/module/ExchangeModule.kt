package com.mockingbird.spinkevich.exchangeme.di.module

import com.mockingbird.spinkevich.exchangeme.di.module.binder.ExchangeBinder
import dagger.Module

@Module(includes = [ExchangeBinder::class, ApplicationModule::class])
class ExchangeModule {
}