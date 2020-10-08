package com.jesusbadenas.oompaloompascrew.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jesusbadenas.oompaloompascrew.R
import com.jesusbadenas.oompaloompascrew.di.GlideApp

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    GlideApp.with(context)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@BindingAdapter("firstName", "lastName")
fun TextView.setName(firstName: String?, lastName: String?) {
    text = context.getString(R.string.ol_full_name, firstName, lastName)
}
