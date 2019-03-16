package com.mockingbird.spinkevich.exchangeme.feature.start

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.R
import com.mockingbird.spinkevich.exchangeme.core.feature.fragment.FeatureFragment
import com.mockingbird.spinkevich.exchangeme.di.graph.StartFragmentGraph
import com.mockingbird.spinkevich.exchangeme.feature.exchange.ExchangeFragment
import com.mockingbird.spinkevich.exchangeme.feature.splash.BF_BASE_COUNTRY
import com.mockingbird.spinkevich.exchangeme.utils.addFragmentToStack
import com.mockingbird.spinkevich.exchangeme.utils.makeGone
import com.mockingbird.spinkevich.exchangeme.utils.makeVisible
import com.mockingbird.spinkevich.exchangeme.utils.putArguments
import kotlinx.android.synthetic.main.fragment_start.permission_allow_button
import kotlinx.android.synthetic.main.fragment_start.permission_refuse_button
import kotlinx.android.synthetic.main.fragment_start.start_content
import kotlinx.android.synthetic.main.fragment_start.start_progress
import kotlinx.android.synthetic.main.fragment_start.start_root_layout

private const val LOCATION_REQUEST_CODE = 811

class StartFragment : FeatureFragment<StartFragmentGraph>(), StartView {

    @InjectPresenter
    lateinit var presenter: StartPresenter

    @ProvidePresenter
    fun providePresenter(): StartPresenter = graph.presenter

    override fun createGraph() = StartFragmentGraph()

    override fun getLayoutRes() = R.layout.fragment_start

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        permission_allow_button.setOnClickListener { requestLocationPermission() }
        permission_refuse_button.setOnClickListener { openAddCurrencyScreen() }
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
        } else {
            presenter.locationPermissionWasGranted()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    presenter.locationPermissionWasGranted()
                } else {
                    presenter.locationPermissionWasRefused()
                }
            }
        }
    }

    override fun openExchangeScreen(baseCountry: Country) {
        val fragment = ExchangeFragment().putArguments {
            putParcelable(BF_BASE_COUNTRY, baseCountry)
        }
        requireActivity().addFragmentToStack(R.id.fragment_container, fragment)
    }

    override fun openAddCurrencyScreen() {
        requireActivity().addFragmentToStack(R.id.fragment_container, ExchangeFragment())
    }

    override fun showUnknownLocationError() {
        Snackbar.make(start_root_layout, getString(R.string.error_detect_location), Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        start_content.makeGone()
        start_progress.makeVisible()
    }

    override fun hideProgress() {
        start_content.makeVisible()
        start_progress.makeGone()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}
