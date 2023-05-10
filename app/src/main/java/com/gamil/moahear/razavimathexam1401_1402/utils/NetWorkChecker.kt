package com.gamil.moahear.razavimathexam1401_1402.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetWorkChecker(private val context: Context) {
    val networkType: NetworkType
        get() {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)) {
                val network = connectivityManager.activeNetwork ?: return NetworkType.NOTHING
                val networkCapabilities =
                    connectivityManager.getNetworkCapabilities(network)
                        ?: return NetworkType.NOTHING
                return when {
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        NetworkType.WIFI
                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        NetworkType.CELLULAR
                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        NetworkType.ETHERNET
                    }
                    else -> {
                        NetworkType.NOTHING
                    }
                }
            } else {
                return when (connectivityManager.activeNetworkInfo?.type) {
                    ConnectivityManager.TYPE_WIFI -> {
                        NetworkType.WIFI
                    }
                    ConnectivityManager.TYPE_MOBILE -> {
                        NetworkType.CELLULAR
                    }
                    ConnectivityManager.TYPE_ETHERNET -> {
                        NetworkType.ETHERNET
                    }
                    else -> NetworkType.NOTHING
                }
            }
        }
}