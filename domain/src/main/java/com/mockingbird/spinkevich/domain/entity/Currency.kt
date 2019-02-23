package com.mockingbird.spinkevich.domain.entity

data class Currency(
    val code: String,
    val name: String,
    val symbol: String
) {
    open fun areItemsTheSame(newItem: Currency) = this == newItem
    open fun areContentsTheSame(newItem: Currency) = this == newItem
}