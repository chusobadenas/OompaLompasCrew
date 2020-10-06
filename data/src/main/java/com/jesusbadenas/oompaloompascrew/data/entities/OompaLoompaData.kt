package com.jesusbadenas.oompaloompascrew.data.entities

import com.google.gson.annotations.SerializedName

data class OompaLoompaData(
    @SerializedName("id") val id: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("height") val height: Int,
    @SerializedName("country") val country: String,
    @SerializedName("age") val age: Int,
    @SerializedName("email") val email: String,
    @SerializedName("profession") val profession: String,
    @SerializedName("image") val image: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("quota") val quota: String? = null,
    @SerializedName("favorite") val favorite: FavoriteData
)
