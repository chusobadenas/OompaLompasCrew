package com.jesusbadenas.oompaloompascrew.data.entities.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class OompaLoompasResponse(
    @SerializedName("current") val current: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("results") val results: List<OompaLoompaData>?
)
