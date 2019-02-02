package com.mockingbird.spinkevich.exchangeme.di.graph

import com.mockingbird.spinkevich.exchangeme.core.feature.FeatureGraph
import com.mockingbird.spinkevich.exchangeme.di.component.ApplicationComponent
import com.mockingbird.spinkevich.exchangeme.feature.newcurrency.NewCurrencyPresenter
import javax.inject.Inject

class NewCurrencyFragmentGraph : FeatureGraph<ApplicationComponent>(ApplicationComponent.Companion) {

    @Inject
    lateinit var presenter: NewCurrencyPresenter

    override fun inject(component: ApplicationComponent) {
        component.inject(this)
    }
}