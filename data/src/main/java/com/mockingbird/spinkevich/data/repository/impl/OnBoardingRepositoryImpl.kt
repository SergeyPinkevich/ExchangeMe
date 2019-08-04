package com.mockingbird.spinkevich.data.repository.impl

import android.text.format.DateUtils
import com.mockingbird.spinkevich.data.repository.OnBoardingRepository
import com.mockingbird.spinkevich.data.source.preferences.ApplicationPreferences
import io.reactivex.Single
import java.util.Calendar
import javax.inject.Inject

private const val PERIOD_FOR_SHOWING_ON_BOARDING = DateUtils.DAY_IN_MILLIS * 3

class OnBoardingRepositoryImpl @Inject constructor(
    private val preferences: ApplicationPreferences
) : OnBoardingRepository {

    override fun isNeedShowOnBoarding(): Single<Boolean> {
        return Single.fromCallable { preferences.isNeedShowOnBoarding }
    }

    override fun setNeedShowOnBoarding(isNeedShow: Boolean) {
        preferences.isNeedShowOnBoarding = isNeedShow
    }

    override fun setLastTimeShown(timeStamp: Long) {
        preferences.lastTimeShowOnBoardding = timeStamp
    }

    override fun isEnoughTimeFromLastShown(): Single<Boolean> {
        return Single.fromCallable {
            Calendar.getInstance().timeInMillis - preferences.lastTimeShowOnBoardding > PERIOD_FOR_SHOWING_ON_BOARDING
        }
    }
}