package com.mockingbird.spinkevich.exchangeme.di.module

import com.mockingbird.spinkevich.exchangeme.di.module.binder.CountryBinder
import dagger.Module

@Module(includes = [CountryBinder::class, ApplicationModule::class])
class NewCurrencyModule