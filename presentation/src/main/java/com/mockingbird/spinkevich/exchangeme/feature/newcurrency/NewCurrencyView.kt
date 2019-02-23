package com.mockingbird.spinkevich.exchangeme.feature.newcurrency

import com.arellomobile.mvp.MvpView
import com.mockingbird.spinkevich.domain.entity.Country

interface NewCurrencyView : MvpView {

    fun showCountriesList(currenciesList: List<Country>)
}