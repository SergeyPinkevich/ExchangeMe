package com.mockingbird.spinkevich.data.interactor

import android.text.format.DateUtils
import com.mockingbird.spinkevich.data.repository.UpdateRepository
import com.mockingbird.spinkevich.domain.usecase.UpdateUseCase
import java.util.Calendar
import javax.inject.Inject

private const val PERIOD_FOR_UPDATE_COUNTRIES = DateUtils.DAY_IN_MILLIS * 3
private const val PERIOD_FOR_UPDATE_RATES = DateUtils.HOUR_IN_MILLIS * 12

class UpdateInteractor @Inject constructor(
    private val updateRepository: UpdateRepository
) : UpdateUseCase {

    override fun isNeedUpdateCountries(): Boolean {
        return Calendar.getInstance().timeInMillis - updateRepository.getLastTimeUpdateCountries() > PERIOD_FOR_UPDATE_COUNTRIES
    }

    override fun isNeedUpdateRates(): Boolean {
        return Calendar.getInstance().timeInMillis - updateRepository.getLastTimeUpdateRates() > PERIOD_FOR_UPDATE_RATES
    }
}