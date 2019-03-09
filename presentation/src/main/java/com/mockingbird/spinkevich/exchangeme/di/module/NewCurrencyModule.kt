package com.mockingbird.spinkevich.exchangeme.di.module

import com.mockingbird.spinkevich.exchangeme.di.module.binder.NewCurrencyBinder
import dagger.Module

@Module(includes = [NewCurrencyBinder::class, ApplicationModule::class])
class NewCurrencyModule