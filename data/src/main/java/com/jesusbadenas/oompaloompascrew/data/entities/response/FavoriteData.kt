package com.jesusbadenas.oompaloompascrew.data.entities.response

import com.google.gson.annotations.SerializedName

data class FavoriteData(
    @SerializedName("color") val color: String,
    @SerializedName("food") val food: String,
    @SerializedName("random_string") val random: String,
    @SerializedName("song") val song: String
)
