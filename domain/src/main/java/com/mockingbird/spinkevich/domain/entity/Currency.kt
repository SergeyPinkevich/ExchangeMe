package com.mockingbird.spinkevich.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currency(
    val code: String,
    val name: String,
    val symbol: String
): Parcelable {
    open fun areItemsTheSame(newItem: Currency) = this == newItem
    open fun areContentsTheSame(newItem: Currency) = this == newItem
}