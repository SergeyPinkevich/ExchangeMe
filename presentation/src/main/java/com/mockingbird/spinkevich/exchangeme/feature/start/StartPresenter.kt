package com.mockingbird.spinkevich.exchangeme.feature.start

import android.location.Geocoder
import android.location.Location
import com.arellomobile.mvp.InjectViewState
import com.mockingbird.spinkevich.data.exceptions.UnknownLocationException
import com.mockingbird.spinkevich.data.utils.location.DetectLocationHelper
import com.mockingbird.spinkevich.domain.interactor.NewCurrencyInteractor
import com.mockingbird.spinkevich.exchangeme.core.BasePresenter
import com.mockingbird.spinkevich.exchangeme.utils.subscribeWithTimberError
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class StartPresenter @Inject constructor(
    private val detectLocationHelper: DetectLocationHelper,
    private val geocoder: Geocoder,
    private val newCurrencyInteractor: NewCurrencyInteractor
) : BasePresenter<StartView>() {

    private lateinit var countryCode: String

    fun locationPermissionWasGranted() {
        unsubscribeOnDestroy(
            Single.fromCallable { detectLocationHelper.getLocation() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { countryCode = getCountryCode(it) }
                .doOnError {
                    if (it is UnknownLocationException) {
                        viewState.showUnknownLocationError()
                    }
                }
                .flatMap { newCurrencyInteractor.getCountriesList() }
                .map { list -> list.first { it.code == countryCode } }
                .subscribeWithTimberError {
                    viewState.openExchangeScreen(it)
                }
        )
    }

    fun locationPermissionWasRefused() {
        viewState.openAddCurrencyScreen()
    }

    private fun getCountryCode(location: Location?): String {
        return location?.let {
            val address = geocoder.getFromLocation(it.latitude, it.longitude, 1).first()
            address.countryCode
        } ?: ""
    }
}