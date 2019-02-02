package com.mockingbird.spinkevich.exchangeme.core.feature

abstract class FeatureGraph<C : Any>(
    private val companion: FeatureComponentCompanion<C>
) {

    fun init() {
        inject(companion.get())
    }

    abstract fun inject(component: C)

    fun onFeatureFinish() {
        companion.onFeatureFinish()
    }
}