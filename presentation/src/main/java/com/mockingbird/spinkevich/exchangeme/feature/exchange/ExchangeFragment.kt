package com.mockingbird.spinkevich.exchangeme.feature.exchange

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.R
import com.mockingbird.spinkevich.exchangeme.core.feature.fragment.FeatureFragment
import com.mockingbird.spinkevich.exchangeme.di.graph.ExchangeFragmentGraph
import com.mockingbird.spinkevich.exchangeme.feature.newcurrency.BF_NEW_COUNTRY
import com.mockingbird.spinkevich.exchangeme.feature.newcurrency.NEW_COUNTRY_REQUEST_CODE
import com.mockingbird.spinkevich.exchangeme.feature.newcurrency.NewCurrencyFragment
import com.mockingbird.spinkevich.exchangeme.feature.splash.BF_BASE_COUNTRY
import com.mockingbird.spinkevich.exchangeme.utils.addFragmentToStack
import com.mockingbird.spinkevich.exchangeme.utils.afterTextChanged
import kotlinx.android.synthetic.main.fragment_exchange.base_currency_amount
import kotlinx.android.synthetic.main.fragment_exchange.base_currency_code
import kotlinx.android.synthetic.main.fragment_exchange.base_currency_flag
import kotlinx.android.synthetic.main.fragment_exchange.base_currency_name
import kotlinx.android.synthetic.main.fragment_exchange.currencies_list

class ExchangeFragment : FeatureFragment<ExchangeFragmentGraph>(), ExchangeView {

    @InjectPresenter
    lateinit var presenter: ExchangePresenter

    @ProvidePresenter
    fun providePresenter(): ExchangePresenter {
        return graph.presenter.apply {
            arguments?.let {
                val baseCountry = it.getParcelable<Country>(BF_BASE_COUNTRY)
                baseCountry?.let { graph.presenter.init(baseCountry) } ?: openNewCurrencyScreen(arrayListOf())
            } ?: openNewCurrencyScreen(arrayListOf())
        }
    }

    private lateinit var adapter: ExchangeAdapter

    override fun createGraph() = ExchangeFragmentGraph()

    override fun getLayoutRes(): Int = R.layout.fragment_exchange

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExchangeAdapter({ presenter.deleteCountry(it) }, { presenter.swapCountryWithBase(it) })
        val itemDecor = DividerItemDecoration(context, ClipDrawable.HORIZONTAL)

        currencies_list.adapter = adapter
        currencies_list.layoutManager = LinearLayoutManager(context)
        currencies_list.addItemDecoration(itemDecor)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.exchange_menu, menu)
        menu?.findItem(R.id.action_add)?.setOnMenuItemClickListener {
            presenter.addCurrencyMenuClicked()
            return@setOnMenuItemClickListener true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_COUNTRY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val country = data?.getParcelableExtra<Country>(BF_NEW_COUNTRY)
            country?.let { presenter.addCountry(country) }
        }
    }

    override fun initializeBaseCountry(country: Country) {
        val resourceId = requireContext().resources.getIdentifier(country.drawableResource, "drawable", requireContext().packageName)
        base_currency_flag.setImageResource(resourceId)
        val currency = country.currency
        base_currency_code.text = currency.code
        base_currency_name.text = "${currency.name} ${currency.symbol}"
        base_currency_amount.afterTextChanged {
            if (it.isNotEmpty()) {
                presenter.convert(it.toFloat())
            } else {
                presenter.convert(0F)
            }
        }
    }

    override fun openNewCurrencyScreen(convertedCountries: ArrayList<Country>) {
        val fragment = NewCurrencyFragment.newInstance(convertedCountries)
        fragment.setTargetFragment(this, NEW_COUNTRY_REQUEST_CODE)
        requireActivity().addFragmentToStack(R.id.fragment_container, fragment)
    }

    override fun updateConvertedCountriesList(convertedCountries: List<Country>) {
        adapter.submitList(convertedCountries)
        adapter.notifyDataSetChanged()
    }

    override fun ratesUpdatesSuccessfully() {
        Snackbar.make(view!!, getString(R.string.successfull_rates_update), Snackbar.LENGTH_SHORT).show()
    }

    override fun ratesUpdatesWithError() {
        val snackbar = Snackbar.make(view!!, getString(R.string.error_rates_update), Snackbar.LENGTH_SHORT)
        snackbar.setAction(R.string.retry) { presenter.updateRates() }
        snackbar.show()
    }
}