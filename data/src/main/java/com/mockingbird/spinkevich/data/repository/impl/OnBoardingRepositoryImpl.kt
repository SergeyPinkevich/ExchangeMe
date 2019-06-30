package com.mockingbird.spinkevich.data.repository.impl

import com.mockingbird.spinkevich.data.repository.OnBoardingRepository
import com.mockingbird.spinkevich.data.source.preferences.ApplicationPreferences
import io.reactivex.Single
import javax.inject.Inject

class OnBoardingRepositoryImpl @Inject constructor(
    private val preferences: ApplicationPreferences
) : OnBoardingRepository {

    override fun isNeedShowOnBoarding(): Single<Boolean> {
        return Single.fromCallable { preferences.isNeedShowOnBoarding }
    }

    override fun setNeedShowOnBoarding(isNeedShow: Boolean) {
        preferences.isNeedShowOnBoarding = isNeedShow
    }
}