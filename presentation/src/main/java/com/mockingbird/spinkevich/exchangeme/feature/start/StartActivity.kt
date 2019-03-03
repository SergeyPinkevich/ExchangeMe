package com.mockingbird.spinkevich.exchangeme.feature.start

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.R
import com.mockingbird.spinkevich.exchangeme.core.feature.activity.FeatureActivity
import com.mockingbird.spinkevich.exchangeme.di.graph.StartActivityGraph
import com.mockingbird.spinkevich.exchangeme.feature.MainActivity
import kotlinx.android.synthetic.main.activity_start.permission_allow_button
import kotlinx.android.synthetic.main.activity_start.permission_refuse_button
import kotlinx.android.synthetic.main.activity_start.root_layout

const val BF_MANUALLY = ".manually"
const val BF_BASE_COUNTRY = ".base.country"
private const val LOCATION_REQUEST_CODE = 811

class StartActivity : FeatureActivity<StartActivityGraph>(), StartView {

    @InjectPresenter
    lateinit var presenter: StartPresenter

    @ProvidePresenter
    fun providePresenter(): StartPresenter = graph.presenter

    override fun createGraph() = StartActivityGraph()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        permission_allow_button.setOnClickListener { requestLocationPermission() }
        permission_refuse_button.setOnClickListener { openAddCurrencyScreen() }
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
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
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(BF_MANUALLY, false)
            putExtra(BF_BASE_COUNTRY, baseCountry)
        }
        startActivity(intent)
    }

    override fun openAddCurrencyScreen() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(BF_MANUALLY, true)
        }
        startActivity(intent)
    }

    override fun showUnknownLocationError() {
        Snackbar.make(root_layout, getString(R.string.error_detect_location), Snackbar.LENGTH_SHORT).show()
    }
}
