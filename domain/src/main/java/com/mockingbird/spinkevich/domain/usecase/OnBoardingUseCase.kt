package com.mockingbird.spinkevich.domain.usecase

import io.reactivex.Single

interface OnBoardingUseCase {

    fun isNeedShowOnBoarding(): Single<Boolean>

    fun setNeedShowOnBoarding(isNeedShow: Boolean)

    fun setLastTimeShownOnBoarding(timeStamp: Long)
}