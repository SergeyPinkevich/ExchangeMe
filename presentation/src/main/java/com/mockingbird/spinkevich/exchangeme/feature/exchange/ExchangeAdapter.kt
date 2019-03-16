package com.mockingbird.spinkevich.exchangeme.feature.exchange

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.R
import com.mockingbird.spinkevich.exchangeme.utils.CountryDiffUtil
import kotlinx.android.synthetic.main.exchange_currency_item.view.exchange_currency_amount
import kotlinx.android.synthetic.main.exchange_currency_item.view.exchange_currency_code
import kotlinx.android.synthetic.main.exchange_currency_item.view.exchange_currency_flag
import kotlinx.android.synthetic.main.exchange_currency_item.view.exchange_currency_name

class ExchangeAdapter : ListAdapter<Country, ExchangeAdapter.ViewHolder>(CountryDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exchange_currency_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(country: Country) {
            val currency = country.currencies.firstOrNull()
            setImage(country)
            itemView.exchange_currency_code.text = currency?.code
            itemView.exchange_currency_amount.text = "123"
            itemView.exchange_currency_name.text = "${currency?.name} ${currency?.symbol}"
        }

        private fun setImage(country: Country) {
            val context = itemView.context
            val id = context.resources.getIdentifier(country.drawableResource, "drawable", context.packageName)
            itemView.exchange_currency_flag.setImageResource(id)
        }
    }
}