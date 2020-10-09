package com.jesusbadenas.oompaloompascrew.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
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

@BindingAdapter("genderBackground")
fun View.setGenderBackground(gender: String?) {
    setBackgroundColor(
        when (gender) {
            "M" -> ContextCompat.getColor(context, R.color.male)
            "F" -> ContextCompat.getColor(context, R.color.female)
            else -> ContextCompat.getColor(context, R.color.white)
        }
    )
}

@BindingAdapter("firstName", "lastName")
fun TextView.setName(firstName: String?, lastName: String?) {
    text = context.getString(R.string.ol_full_name, firstName, lastName)
}

@BindingAdapter("detailAge")
fun TextView.setDetailAge(age: Int?) {
    text = context.getString(R.string.detail_age, age)
}

@BindingAdapter("detailCountry")
fun TextView.setDetailCountry(country: String?) {
    text = context.getString(R.string.detail_country, country)
}

@BindingAdapter("detailDescription")
fun TextView.setDetailDescription(description: String?) {
    text = context.getString(R.string.detail_description, description)
}

@BindingAdapter("detailEmail")
fun TextView.setDetailEmail(email: String?) {
    text = context.getString(R.string.detail_email, email)
}

@BindingAdapter("detailGender")
fun TextView.setDetailGender(gender: String?) {
    text = context.getString(R.string.detail_gender, gender)
}

@BindingAdapter("detailHeight")
fun TextView.setDetailHeight(height: Int?) {
    text = context.getString(R.string.detail_height, height)
}

@BindingAdapter("detailProfession")
fun TextView.setDetailProfession(profession: String?) {
    text = context.getString(R.string.detail_profession, profession)
}
