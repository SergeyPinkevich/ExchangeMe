package com.mockingbird.spinkevich.exchangeme.feature.core

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.R
import com.mockingbird.spinkevich.exchangeme.core.feature.activity.FeatureActivity
import com.mockingbird.spinkevich.exchangeme.di.graph.MainActivityGraph
import com.mockingbird.spinkevich.exchangeme.feature.exchange.ExchangeFragment
import com.mockingbird.spinkevich.exchangeme.feature.splash.BF_BASE_COUNTRY
import com.mockingbird.spinkevich.exchangeme.feature.start.StartFragment
import com.mockingbird.spinkevich.exchangeme.utils.addFragmentToStack
import com.mockingbird.spinkevich.exchangeme.utils.putArguments

class MainActivity : FeatureActivity<MainActivityGraph>(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = graph.presenter

    override fun createGraph() = MainActivityGraph()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent?.let {
            val baseCountry = it.extras?.getParcelable<Country>(BF_BASE_COUNTRY)
            val fragment: Fragment = if (baseCountry != null) {
                ExchangeFragment().putArguments {
                    putParcelable(BF_BASE_COUNTRY, baseCountry)
                }
            } else {
                StartFragment()
            }
            addFragmentToStack(R.id.fragment_container, fragment)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            presenter.checkBackButtonClickedTwice()
        } else {
            super.onBackPressed()
        }
    }

    override fun showClickBackButtonAgain() {
        Toast.makeText(this, R.string.click_again_to_exit, Toast.LENGTH_SHORT).show()
    }

    override fun closeApp() {
        finish()
    }
}
