package com.mockingbird.spinkevich.exchangeme.di.module

import android.content.Context
import com.mockingbird.spinkevich.data.utils.location.DetectLocationHelper
import com.mockingbird.spinkevich.data.utils.location.DetectLocationHelperImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class])
class StartModule {

    @Provides
    fun provideDetectLocationHelper(context: Context): DetectLocationHelper {
        return DetectLocationHelperImpl(context)
    }
}