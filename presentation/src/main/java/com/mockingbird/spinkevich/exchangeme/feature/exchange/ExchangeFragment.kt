package com.mockingbird.spinkevich.exchangeme.feature.exchange

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.R
import com.mockingbird.spinkevich.exchangeme.core.feature.FeatureFragment
import com.mockingbird.spinkevich.exchangeme.di.graph.ExchangeFragmentGraph
import kotlinx.android.synthetic.main.fragment_exchange.base_currency_amount
import kotlinx.android.synthetic.main.fragment_exchange.base_currency_code
import kotlinx.android.synthetic.main.fragment_exchange.base_currency_flag
import kotlinx.android.synthetic.main.fragment_exchange.base_currency_name

class ExchangeFragment : FeatureFragment<ExchangeFragmentGraph>(), ExchangeView {

    @InjectPresenter
    lateinit var presenter: ExchangePresenter

    @ProvidePresenter
    fun providePresenter(): ExchangePresenter {
        return graph.presenter
    }

    override fun createGraph() = ExchangeFragmentGraph()

    override fun getLayoutRes(): Int = R.layout.fragment_exchange

    override fun initializeBaseCountry(country: Country) {
        val resourceId = requireContext().resources.getIdentifier(country.drawableResource, "drawable", requireContext().packageName)
        base_currency_flag.setImageResource(resourceId)
        base_currency_code.text = country.currencies.first().code
        base_currency_amount.setText("100")
        base_currency_name.text = "${country.currencies.first().name} ${country.currencies.first().symbol}"
    }
}