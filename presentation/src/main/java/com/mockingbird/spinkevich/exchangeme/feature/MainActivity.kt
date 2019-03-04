package com.mockingbird.spinkevich.exchangeme.feature

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.R
import com.mockingbird.spinkevich.exchangeme.feature.exchange.ExchangeFragment
import com.mockingbird.spinkevich.exchangeme.feature.newcurrency.NewCurrencyFragment
import com.mockingbird.spinkevich.exchangeme.feature.start.BF_BASE_COUNTRY
import com.mockingbird.spinkevich.exchangeme.feature.start.BF_MANUALLY
import com.mockingbird.spinkevich.exchangeme.utils.addFragmentToStack

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent?.let {
            if (it.extras.containsKey(BF_MANUALLY) && it.getBooleanExtra(BF_MANUALLY, true)) {
                addFragmentToStack(R.id.fragment_container, ExchangeFragment())
                addFragmentToStack(R.id.fragment_container, NewCurrencyFragment())
            } else {
                val country = it.getParcelableExtra<Country>(BF_BASE_COUNTRY)
                val fragment = ExchangeFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(BF_BASE_COUNTRY, country)
                    }
                }
                addFragmentToStack(R.id.fragment_container, fragment)
            }
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
