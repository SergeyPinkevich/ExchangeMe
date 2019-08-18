package com.mockingbird.spinkevich.exchangeme.feature.privacypolicy

import android.os.Bundle
import android.view.View
import com.mockingbird.spinkevich.exchangeme.R
import com.mockingbird.spinkevich.exchangeme.core.feature.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_privacy_policy.web_view

private const val PRIVACY_POLICY_PATH = "file:///android_asset/privacy_policy.html"

class PrivacyPolicyFragment : BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_privacy_policy

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        web_view.loadUrl(PRIVACY_POLICY_PATH)
    }
}