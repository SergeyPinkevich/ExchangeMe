package com.mockingbird.spinkevich.exchangeme.di

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.mockingbird.spinkevich.exchangeme.di.component.ApplicationComponent
import com.mockingbird.spinkevich.exchangeme.di.component.DaggerApplicationComponent
import com.mockingbird.spinkevich.exchangeme.di.module.ApplicationModule
import io.fabric.sdk.android.Fabric

class BaseApp : Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())

        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}