package com.mockingbird.spinkevich.domain.entity

data class Country(
    val name: String,
    val region: String,
    val subRegion: String,
    val flag: String,
    val currencies: List<Currency>
) {
    open fun areItemsTheSame(newItem: Country) = this == newItem
    open fun areContentsTheSame(newItem: Country) = this == newItem

    val drawableResource: String
    get() = name.replace("\\s+".toRegex(), "").toLowerCase()
}