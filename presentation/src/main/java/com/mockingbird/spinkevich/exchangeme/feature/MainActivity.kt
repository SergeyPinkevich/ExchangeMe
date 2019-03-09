package com.mockingbird.spinkevich.exchangeme.feature

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.R
import com.mockingbird.spinkevich.exchangeme.feature.exchange.ExchangeFragment
import com.mockingbird.spinkevich.exchangeme.feature.splash.BF_BASE_COUNTRY
import com.mockingbird.spinkevich.exchangeme.feature.start.StartFragment
import com.mockingbird.spinkevich.exchangeme.utils.addFragmentToStack
import com.mockingbird.spinkevich.exchangeme.utils.putArguments

class MainActivity : AppCompatActivity() {

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
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
