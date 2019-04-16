package com.mockingbird.spinkevich.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val code: String,
    val name: String,
    val region: String,
    val subRegion: String,
    val currency: Currency
): Parcelable {

    open fun areItemsTheSame(newItem: Country) = this == newItem

    open fun areContentsTheSame(newItem: Country) = this == newItem

    val drawableResource: String
    get() = name.replace("\\s+".toRegex(), "").toLowerCase()
}