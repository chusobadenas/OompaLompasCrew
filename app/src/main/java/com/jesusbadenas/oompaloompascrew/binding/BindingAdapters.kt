package com.jesusbadenas.oompaloompascrew.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy
import com.jesusbadenas.oompaloompascrew.R

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    load(url) {
        crossfade(true)
        diskCachePolicy(CachePolicy.ENABLED)
        memoryCachePolicy(CachePolicy.ENABLED)
        placeholder(R.drawable.ic_launcher_background)
    }
}

@BindingAdapter("firstName", "lastName")
fun TextView.setName(firstName: String, lastName: String) {
    text = context.getString(R.string.ol_full_name, firstName, lastName)
}
