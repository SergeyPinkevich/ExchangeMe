package com.mockingbird.spinkevich.exchangeme.core.feature

import android.os.Bundle
import com.mockingbird.spinkevich.exchangeme.core.BaseFragment

abstract class FeatureFragment<G : FeatureGraph<*>> : BaseFragment() {

    val graph by lazy { uninitializedGraph.apply { init() } }

    private val uninitializedGraph by lazy { createGraph() }
    private val featureFragmentDelegate by lazy { FeatureFragmentDelegate(this) { uninitializedGraph } }

    abstract fun createGraph(): G

    override fun onStart() {
        super.onStart()
        featureFragmentDelegate.onStart()
    }

    override fun onResume() {
        super.onResume()
        featureFragmentDelegate.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        featureFragmentDelegate.onSaveInstanceState()
    }

    override fun onDestroy() {
        super.onDestroy()
        featureFragmentDelegate.onDestroy()
    }
}