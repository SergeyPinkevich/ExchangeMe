package com.mockingbird.spinkevich.exchangeme.feature.newcurrency

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.R
import com.mockingbird.spinkevich.exchangeme.core.feature.FeatureFragment
import com.mockingbird.spinkevich.exchangeme.di.graph.NewCurrencyFragmentGraph
import com.mockingbird.spinkevich.exchangeme.utils.onQueryTextChange
import kotlinx.android.synthetic.main.fragment_new_currency.currencies_list

class NewCurrencyFragment : FeatureFragment<NewCurrencyFragmentGraph>(), NewCurrencyView {

    @InjectPresenter
    lateinit var presenter: NewCurrencyPresenter

    @ProvidePresenter
    fun providePresenter(): NewCurrencyPresenter {
        return graph.presenter
    }

    private lateinit var adapter: NewCurrencyAdapter

    override fun createGraph() = NewCurrencyFragmentGraph()

    override fun getLayoutRes(): Int = R.layout.fragment_new_currency

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.onQueryTextChange { presenter.filterCurrencies(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewCurrencyAdapter()
        val itemDecor = DividerItemDecoration(context, HORIZONTAL)

        currencies_list.adapter = adapter
        currencies_list.layoutManager = LinearLayoutManager(context)
        currencies_list.addItemDecoration(itemDecor)

        presenter.loadCurrencies()
    }

    override fun showCountriesList(currenciesList: List<Country>) {
        val filterList = currenciesList.filter { checkIfCountryHasFlag(it) }
        adapter.submitList(filterList)
    }

    private fun checkIfCountryHasFlag(country: Country): Boolean {
        val id = context?.resources?.getIdentifier(country.drawableResource, "drawable", requireContext().packageName)
        if (id == 0) {
            return false
        }
        return true
    }
}
