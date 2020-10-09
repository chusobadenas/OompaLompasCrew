package com.jesusbadenas.oompaloompascrew.util

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.jesusbadenas.oompaloompascrew.R
import com.jesusbadenas.oompaloompascrew.entities.UIError
import timber.log.Timber

fun <T> MutableList<T>.clearAndAdd(items: List<T>) {
    clear()
    addAll(items)
}

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

fun Fragment.hideKeyboard(view: View) {
    (context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        hideSoftInputFromWindow(view.windowToken, 0)
    }
}
