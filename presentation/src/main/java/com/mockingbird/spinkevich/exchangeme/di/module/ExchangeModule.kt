package com.mockingbird.spinkevich.exchangeme.di.module

import com.mockingbird.spinkevich.exchangeme.di.module.binder.RatesBinder
import dagger.Module

@Module(includes = [RatesBinder::class, ApplicationModule::class])
class ExchangeModule