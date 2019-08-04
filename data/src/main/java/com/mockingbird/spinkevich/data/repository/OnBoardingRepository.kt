package com.mockingbird.spinkevich.data.repository

import io.reactivex.Single

interface OnBoardingRepository {

    fun isNeedShowOnBoarding(): Single<Boolean>

    fun setNeedShowOnBoarding(isNeedShow: Boolean)

    fun setLastTimeShown(timeStamp: Long)

    fun isEnoughTimeFromLastShown(): Single<Boolean>
}