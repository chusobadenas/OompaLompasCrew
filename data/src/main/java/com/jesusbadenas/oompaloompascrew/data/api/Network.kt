package com.jesusbadenas.oompaloompascrew.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun hasNetwork(context: Context): Boolean {
    var isConnected = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                isConnected = true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                isConnected = true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                isConnected = true
            }
        }
    }
    return isConnected
}
