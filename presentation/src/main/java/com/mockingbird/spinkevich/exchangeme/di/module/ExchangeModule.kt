package com.mockingbird.spinkevich.exchangeme.di.module

import com.mockingbird.spinkevich.exchangeme.di.module.binder.OnBoardingBinder
import com.mockingbird.spinkevich.exchangeme.di.module.binder.RatesBinder
import dagger.Module

@Module(includes = [OnBoardingBinder::class, RatesBinder::class, ApplicationModule::class])
class ExchangeModule