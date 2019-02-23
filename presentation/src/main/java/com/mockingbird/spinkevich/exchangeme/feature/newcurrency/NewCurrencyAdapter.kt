package com.mockingbird.spinkevich.exchangeme.feature.newcurrency

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mockingbird.spinkevich.domain.entity.Country
import com.mockingbird.spinkevich.exchangeme.R
import kotlinx.android.synthetic.main.search_currency_item.view.search_currency_country
import kotlinx.android.synthetic.main.search_currency_item.view.search_currency_flag
import kotlinx.android.synthetic.main.search_currency_item.view.search_currency_name

class NewCurrencyAdapter : ListAdapter<Country, NewCurrencyAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_currency_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(country: Country) {
            val currency = country.currencies[0]
            setImage(country)
            itemView.search_currency_country.text = country.name
            itemView.search_currency_name.text = "${currency.name}, ${currency.symbol}"
        }

        private fun setImage(country: Country) {
            val context = itemView.context
            val id = context.resources.getIdentifier(country.drawableResource, "drawable", context.packageName)
            itemView.search_currency_flag.setImageResource(id)
        }
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Country>() {

            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem.areItemsTheSame(newItem)
            }

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem.areContentsTheSame(newItem)
            }
        }
    }
}

