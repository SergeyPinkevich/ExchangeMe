package com.mockingbird.spinkevich.exchangeme.di.module

import android.content.Context
import android.location.Geocoder
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import java.util.Locale

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideGeocoder(context: Context): Geocoder {
        return Geocoder(context, Locale.getDefault())
    }
}