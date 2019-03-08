package com.mockingbird.spinkevich.exchangeme.utils

import android.support.v7.util.DiffUtil
import com.mockingbird.spinkevich.domain.entity.Country

object CountryDiffUtil : DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}