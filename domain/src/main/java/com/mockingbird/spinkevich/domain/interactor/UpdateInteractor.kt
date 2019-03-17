package com.mockingbird.spinkevich.domain.interactor

import android.text.format.DateUtils
import com.mockingbird.spinkevich.domain.repository.UpdateRepository
import com.mockingbird.spinkevich.domain.usecase.UpdateUseCase
import java.util.Calendar
import javax.inject.Inject

private const val PERIOD_FOR_UPDATE = DateUtils.HOUR_IN_MILLIS * 12

class UpdateInteractor @Inject constructor(
    private val updateRepository: UpdateRepository
) : UpdateUseCase {

    override fun isNeedUpdate(): Boolean {
        return Calendar.getInstance().timeInMillis - updateRepository.getLastTimeUpdate() > PERIOD_FOR_UPDATE
    }
}