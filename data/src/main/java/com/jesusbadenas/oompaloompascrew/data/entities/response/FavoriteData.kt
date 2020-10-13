package com.jesusbadenas.oompaloompascrew.data.entities.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class FavoriteData(
    @SerializedName("color") val color: String,
    @SerializedName("food") val food: String,
    @SerializedName("random_string") val random: String,
    @SerializedName("song") val song: String
)
