package com.jesusbadenas.oompaloompascrew.util

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.jesusbadenas.oompaloompascrew.R
import com.jesusbadenas.oompaloompascrew.entities.UIError
import timber.log.Timber

fun <T> MutableLiveData<MutableList<T>>.addMoreItems(items: List<T>) {
    val oldValue = value ?: mutableListOf()
    oldValue.addAll(items)
    value = oldValue
}

fun Fragment.showError(uiError: UIError) {
    // Log error
    Timber.e(uiError.throwable)
    // Show dialog
    AlertDialog.Builder(context).apply {
        setCancelable(false)
        setTitle(R.string.generic_error_title)
        setMessage(uiError.messageResId)
        setNeutralButton(android.R.string.ok, null)
        create()
    }.show()
}
