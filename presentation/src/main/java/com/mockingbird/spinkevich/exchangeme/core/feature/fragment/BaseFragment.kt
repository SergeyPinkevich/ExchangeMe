package com.mockingbird.spinkevich.exchangeme.core.feature.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment

open class BaseFragment : MvpAppCompatFragment() {

    open fun getLayoutRes() = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (getLayoutRes() != -1) {
            inflater.inflate(getLayoutRes(), container, false)
        } else super.onCreateView(inflater, container, savedInstanceState)
    }
}