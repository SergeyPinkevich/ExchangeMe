package com.mockingbird.spinkevich.exchangeme.core.feature

abstract class FeatureComponentCompanion<C : Any> {

    abstract fun createComponent(): C

    fun get(): C {
        return FeatureComponentHolder.getOrCreate<C>(this)
    }

    fun onFeatureFinish() {
        FeatureComponentHolder.destroyIfNeeded(this)
    }
}