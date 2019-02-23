package com.mockingbird.spinkevich.exchangeme.di.graph

import com.mockingbird.spinkevich.exchangeme.core.feature.FeatureGraph
import com.mockingbird.spinkevich.exchangeme.di.component.ApplicationComponent
import com.mockingbird.spinkevich.exchangeme.feature.exchange.ExchangePresenter
import javax.inject.Inject

class ExchangeFragmentGraph : FeatureGraph<ApplicationComponent>(ApplicationComponent.Companion) {

    @Inject
    lateinit var presenter: ExchangePresenter

    override fun inject(component: ApplicationComponent) {
        component.inject(this)
    }
}