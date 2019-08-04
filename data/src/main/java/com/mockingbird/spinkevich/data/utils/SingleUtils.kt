package com.mockingbird.spinkevich.data.utils

import io.reactivex.Single

object SingleUtils {

    fun checkConditions(vararg singles: Single<Boolean>): Single<Boolean> {
        val list = singles.toList()
        return Single.zip(list) { conditions -> conditions.none { it == false } }
    }
}