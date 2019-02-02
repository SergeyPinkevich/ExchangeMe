package com.mockingbird.spinkevich.exchangeme.di

import android.app.Application
import com.mockingbird.spinkevich.exchangeme.di.component.ApplicationComponent
import com.mockingbird.spinkevich.exchangeme.di.component.DaggerApplicationComponent
import com.mockingbird.spinkevich.exchangeme.di.module.ApplicationModule

class BaseApp : Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}