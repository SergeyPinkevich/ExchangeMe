package com.mockingbird.spinkevich.exchangeme.feature.exchange

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daimajia.swipe.SwipeLayout
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.R
import com.mockingbird.spinkevich.exchangeme.utils.CountryDiffUtil
import kotlinx.android.synthetic.main.exchange_currency_item.view.bottom_wrapper_layout
import kotlinx.android.synthetic.main.exchange_currency_item.view.delete_layout
import kotlinx.android.synthetic.main.exchange_currency_item.view.exchange_currency_amount
import kotlinx.android.synthetic.main.exchange_currency_item.view.exchange_currency_code
import kotlinx.android.synthetic.main.exchange_currency_item.view.exchange_currency_flag
import kotlinx.android.synthetic.main.exchange_currency_item.view.exchange_currency_name
import kotlinx.android.synthetic.main.exchange_currency_item.view.swap_layout
import kotlinx.android.synthetic.main.exchange_currency_item.view.swipe_layout

class ExchangeAdapter(
    private val deleteCallback: (Country) -> Unit,
    private val swapCallback: (Country) -> Unit
) : ListAdapter<Country, ExchangeAdapter.ViewHolder>(CountryDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exchange_currency_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), deleteCallback, swapCallback)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(country: Country, deleteCallback: (Country) -> Unit, swapCallback: (Country) -> Unit) {
            bindViewItem(country)
            initSwipeLayout()
            initDeleteLayout(country, deleteCallback)
            initSwapLayout(country, swapCallback)
        }

        private fun bindViewItem(country: Country) {
            val context = itemView.context
            val id = context.resources.getIdentifier(country.drawableResource, "drawable", context.packageName)
            itemView.exchange_currency_flag.setImageResource(id)

            val currency = country.currency
            itemView.exchange_currency_code.text = currency.code
            itemView.exchange_currency_amount.text = formatAmount(currency.amount)
            itemView.exchange_currency_name.text = "${currency.name} ${currency.symbol}"
        }

        private fun formatAmount(amount: Float): String {
            return if (amount == 0.0F) {
                "0"
            } else {
                String.format("%.2f", amount)
            }
        }

        private fun initSwipeLayout() {
            itemView.swipe_layout.showMode = SwipeLayout.ShowMode.PullOut
            itemView.swipe_layout.addDrag(SwipeLayout.DragEdge.Left, itemView.bottom_wrapper_layout)
        }

        private fun initDeleteLayout(country: Country, deleteCallback: (Country) -> Unit) {
            itemView.delete_layout.setOnClickListener { deleteCallback.invoke(country) }
        }

        private fun initSwapLayout(country: Country, swapCallback: (Country) -> Unit) {
            itemView.swap_layout.setOnClickListener { swapCallback.invoke(country) }
        }
    }
}