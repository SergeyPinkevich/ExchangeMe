package com.mockingbird.spinkevich.exchangeme.feature.splash

import android.content.Intent
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.core.feature.activity.FeatureActivity
import com.mockingbird.spinkevich.exchangeme.di.graph.SplashActivityGraph
import com.mockingbird.spinkevich.exchangeme.feature.core.MainActivity

const val BF_BASE_COUNTRY = ".base.country"

class SplashActivity : FeatureActivity<SplashActivityGraph>(), SplashView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter() = graph.presenter

    override fun createGraph() = SplashActivityGraph()

    override fun openMainScreen(baseCountry: Country?) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(BF_BASE_COUNTRY, baseCountry)
        }
        startActivity(intent)
    }
}