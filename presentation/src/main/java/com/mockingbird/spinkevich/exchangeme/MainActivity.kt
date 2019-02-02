package com.mockingbird.spinkevich.exchangeme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mockingbird.spinkevich.exchangeme.feature.newcurrency.NewCurrencyFragment
import com.mockingbird.spinkevich.exchangeme.utils.addFragmentToStack

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragmentToStack(R.id.fragment_container, NewCurrencyFragment())
    }
}
