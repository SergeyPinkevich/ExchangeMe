package com.mockingbird.spinkevich.exchangeme.core.feature.activity

import com.arellomobile.mvp.MvpAppCompatActivity
import com.mockingbird.spinkevich.exchangeme.core.feature.FeatureGraph

abstract class FeatureActivity<G : FeatureGraph<*>> : MvpAppCompatActivity() {

    val graph by lazy { uninitializedGraph.apply { init() } }

    private val uninitializedGraph by lazy { createGraph() }

    abstract fun createGraph(): G

    override fun onDestroy() {
        super.onDestroy()

        if (isFinishing) {
            uninitializedGraph.onFeatureFinish()
        }
    }
}