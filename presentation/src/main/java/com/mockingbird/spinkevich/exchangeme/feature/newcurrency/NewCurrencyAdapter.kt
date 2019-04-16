package com.mockingbird.spinkevich.exchangeme.feature.newcurrency

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.R
import com.mockingbird.spinkevich.exchangeme.utils.CountryDiffUtil
import kotlinx.android.synthetic.main.search_currency_item.view.search_currency_country
import kotlinx.android.synthetic.main.search_currency_item.view.search_currency_flag
import kotlinx.android.synthetic.main.search_currency_item.view.search_currency_name

class NewCurrencyAdapter(private val clickListener: (Country) -> Unit) : ListAdapter<Country, NewCurrencyAdapter.ViewHolder>(CountryDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_currency_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(country: Country, clickListener: (Country) -> Unit) {
            val currency = country.currency
            setImage(country)
            itemView.search_currency_country.text = country.name
            itemView.search_currency_name.text = "${currency.name}, ${currency.symbol}"
            itemView.setOnClickListener { clickListener(country) }
        }

        private fun setImage(country: Country) {
            val context = itemView.context
            val id = context.resources.getIdentifier(country.drawableResource, "drawable", context.packageName)
            itemView.search_currency_flag.setImageResource(id)
        }
    }
}

