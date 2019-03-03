package com.mockingbird.spinkevich.data.entity

import com.google.gson.annotations.SerializedName

data class CountriesInfo(
    @SerializedName("countries")
    val list: List<Country>
)

data class Country(
    @SerializedName("alpha2Code")
    val alpha2Code: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("region")
    val region: String?,
    @SerializedName("subregion")
    val subregion: String?,
    @SerializedName("flag")
    val flag: String?,
    @SerializedName("currencies")
    val currencies: List<Currency>?
)

data class Currency(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?
)