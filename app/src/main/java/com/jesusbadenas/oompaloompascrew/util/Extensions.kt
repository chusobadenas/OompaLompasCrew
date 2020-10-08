package com.jesusbadenas.oompaloompascrew.util

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<MutableList<T>>.addMoreItems(items: List<T>) {
    val oldValue = value ?: mutableListOf()
    oldValue.addAll(items)
    value = oldValue
}
