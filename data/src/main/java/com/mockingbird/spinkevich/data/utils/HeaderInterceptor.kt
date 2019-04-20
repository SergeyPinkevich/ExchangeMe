package com.mockingbird.spinkevich.data.utils

import android.os.Build
import android.os.LocaleList
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale

private const val HEADER_LANGUAGE_NAME = "Accept-Language"

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentLanguage = getLanguage()
        val requestWithHeaders = chain.request()
            .newBuilder()
            .addHeader(HEADER_LANGUAGE_NAME, currentLanguage)
            .build()
        return chain.proceed(requestWithHeaders)
    }


    private fun getLanguage(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList.getDefault().toLanguageTags()
        } else {
            Locale.getDefault().language
        }
    }
}