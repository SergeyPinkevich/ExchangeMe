package com.mockingbird.spinkevich.exchangeme.feature.newcurrency

import com.arellomobile.mvp.MvpView
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.core.ShowHideProgress

interface NewCurrencyView : MvpView, ShowHideProgress {

    fun showCountriesList(currenciesList: List<Country>)
}