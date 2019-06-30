package com.mockingbird.spinkevich.data.interactor

import com.mockingbird.spinkevich.data.repository.OnBoardingRepository
import com.mockingbird.spinkevich.domain.usecase.OnBoardingUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OnBoardingInteractor @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository
) : OnBoardingUseCase {

    override fun isNeedShowOnBoarding(): Single<Boolean> {
        return onBoardingRepository.isNeedShowOnBoarding()
            .subscribeOn(Schedulers.io())
    }

    override fun setNeedShowOnBoarding(isNeedShow: Boolean) {
        onBoardingRepository.setNeedShowOnBoarding(isNeedShow)
    }
}