package com.mockingbird.spinkevich.exchangeme.di.graph

import com.mockingbird.spinkevich.exchangeme.core.feature.FeatureGraph
import com.mockingbird.spinkevich.exchangeme.di.component.ApplicationComponent
import com.mockingbird.spinkevich.exchangeme.feature.start.StartPresenter
import javax.inject.Inject

class StartFragmentGraph : FeatureGraph<ApplicationComponent>(ApplicationComponent.Companion) {

    @Inject
    lateinit var presenter: StartPresenter

    override fun inject(component: ApplicationComponent) {
        component.inject(this)
    }
}