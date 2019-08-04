package com.mockingbird.spinkevich.data.interactor

import com.mockingbird.spinkevich.data.repository.OnBoardingRepository
import com.mockingbird.spinkevich.data.utils.SingleUtils
import com.mockingbird.spinkevich.domain.usecase.OnBoardingUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OnBoardingInteractor @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository
) : OnBoardingUseCase {

    override fun isNeedShowOnBoarding(): Single<Boolean> {
        return SingleUtils.checkConditions(
            onBoardingRepository.isNeedShowOnBoarding(),
            onBoardingRepository.isEnoughTimeFromLastShown()
        ).subscribeOn(Schedulers.io())
    }

    override fun setNeedShowOnBoarding(isNeedShow: Boolean) {
        onBoardingRepository.setNeedShowOnBoarding(isNeedShow)
    }

    override fun setLastTimeShownOnBoarding(timeStamp: Long) {
        onBoardingRepository.setLastTimeShown(timeStamp)
    }
}