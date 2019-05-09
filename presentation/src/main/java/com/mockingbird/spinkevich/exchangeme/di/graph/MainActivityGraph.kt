package com.mockingbird.spinkevich.exchangeme.di.graph

import com.mockingbird.spinkevich.exchangeme.core.feature.FeatureGraph
import com.mockingbird.spinkevich.exchangeme.di.component.ApplicationComponent
import com.mockingbird.spinkevich.exchangeme.feature.core.MainPresenter
import javax.inject.Inject

class MainActivityGraph : FeatureGraph<ApplicationComponent>(ApplicationComponent.Companion) {

    @Inject
    lateinit var presenter: MainPresenter

    override fun inject(component: ApplicationComponent) {
        component.inject(this)
    }
}