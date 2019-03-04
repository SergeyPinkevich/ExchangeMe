package com.mockingbird.spinkevich.exchangeme.feature.exchange

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ClipDrawable
import android.os.Bundle
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
import com.mockingbird.spinkevich.exchangeme.feature.newcurrency.NEW_CURRENCY_REQUEST_CODE
import com.mockingbird.spinkevich.exchangeme.feature.newcurrency.NewCurrencyFragment
import com.mockingbird.spinkevich.exchangeme.feature.start.BF_BASE_COUNTRY
import com.mockingbird.spinkevich.exchangeme.utils.addFragmentToStack
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
        val presenter = graph.presenter
        arguments?.let {
            presenter.init(it.getParcelable(BF_BASE_COUNTRY))
        }
        return graph.presenter
    }

    private lateinit var adapter: ExchangeAdapter

    override fun createGraph() = ExchangeFragmentGraph()

    override fun getLayoutRes(): Int = R.layout.fragment_exchange

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExchangeAdapter()
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
        if (requestCode == NEW_CURRENCY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val country = data?.getParcelableExtra<Country>(BF_NEW_COUNTRY)
            country?.let { presenter.addCountry(country) }
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    override fun initializeBaseCountry(country: Country) {
        val resourceId = requireContext().resources.getIdentifier(country.drawableResource, "drawable", requireContext().packageName)
        base_currency_flag.setImageResource(resourceId)
        val currency = country.currencies.firstOrNull()
        base_currency_code.text = currency?.code
        base_currency_amount.setText("100")
        base_currency_name.text = "${currency?.name} ${currency?.symbol}"
    }

    override fun openNewCurrencyScreen() {
        val fragment = NewCurrencyFragment()
        fragment.setTargetFragment(this, NEW_CURRENCY_REQUEST_CODE)
        requireActivity().addFragmentToStack(R.id.fragment_container, fragment)
    }

    override fun updateCountriesList(country: List<Country>) {
        adapter.submitList(country)
    }
}