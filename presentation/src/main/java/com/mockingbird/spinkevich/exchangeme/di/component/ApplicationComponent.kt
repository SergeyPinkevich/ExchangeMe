package com.mockingbird.spinkevich.exchangeme.di.component

import com.mockingbird.spinkevich.exchangeme.core.feature.FeatureComponentCompanion
import com.mockingbird.spinkevich.exchangeme.di.BaseApp
import com.mockingbird.spinkevich.exchangeme.di.graph.NewCurrencyFragmentGraph
import com.mockingbird.spinkevich.exchangeme.di.module.ApplicationModule
import com.mockingbird.spinkevich.exchangeme.di.module.NewCurrencyModule
import dagger.Component

@Component(modules = [ApplicationModule::class, NewCurrencyModule::class])
interface ApplicationComponent {

    fun inject(graph: NewCurrencyFragmentGraph)

    companion object : FeatureComponentCompanion<ApplicationComponent>() {

        override fun createComponent(): ApplicationComponent = BaseApp.appComponent
    }
}