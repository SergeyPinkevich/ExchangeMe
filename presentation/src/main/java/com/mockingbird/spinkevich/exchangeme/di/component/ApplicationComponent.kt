package com.mockingbird.spinkevich.exchangeme.di.component

import com.mockingbird.spinkevich.exchangeme.core.feature.FeatureComponentCompanion
import com.mockingbird.spinkevich.exchangeme.di.BaseApp
import com.mockingbird.spinkevich.exchangeme.di.graph.ExchangeFragmentGraph
import com.mockingbird.spinkevich.exchangeme.di.graph.NewCurrencyFragmentGraph
import com.mockingbird.spinkevich.exchangeme.di.graph.StartActivityGraph
import com.mockingbird.spinkevich.exchangeme.di.module.ApiModule
import com.mockingbird.spinkevich.exchangeme.di.module.ApplicationModule
import com.mockingbird.spinkevich.exchangeme.di.module.ExchangeModule
import com.mockingbird.spinkevich.exchangeme.di.module.NewCurrencyModule
import com.mockingbird.spinkevich.exchangeme.di.module.StartModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, StartModule::class, NewCurrencyModule::class, ExchangeModule::class, ApiModule::class])
interface ApplicationComponent {

    fun inject(graph: StartActivityGraph)

    fun inject(graph: NewCurrencyFragmentGraph)

    fun inject(graph: ExchangeFragmentGraph)

    companion object : FeatureComponentCompanion<ApplicationComponent>() {

        override fun createComponent(): ApplicationComponent = BaseApp.appComponent
    }
}