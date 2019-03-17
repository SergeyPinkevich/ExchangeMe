package com.mockingbird.spinkevich.exchangeme.di.module

import com.mockingbird.spinkevich.exchangeme.di.module.binder.UpdateBinder
import dagger.Module

@Module(includes = [UpdateBinder::class])
class UpdateModule