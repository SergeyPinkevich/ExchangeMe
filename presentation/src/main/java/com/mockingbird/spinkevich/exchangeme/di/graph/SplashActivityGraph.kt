package com.mockingbird.spinkevich.exchangeme.di.graph

import com.mockingbird.spinkevich.exchangeme.core.feature.FeatureGraph
import com.mockingbird.spinkevich.exchangeme.di.component.ApplicationComponent
import com.mockingbird.spinkevich.exchangeme.feature.splash.SplashPresenter
import javax.inject.Inject

class SplashActivityGraph : FeatureGraph<ApplicationComponent>(ApplicationComponent.Companion) {

    @Inject
    lateinit var presenter: SplashPresenter

    override fun inject(component: ApplicationComponent) {
        component.inject(this)
    }
}