package com.macrohard.androidutils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Checks for network availability
 * NOTE: Don't forget to add android.permission.ACCESS_NETWORK_STATE permission to manifest
 * @return a boolean representing if network is available or not
 */
fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = cm.activeNetworkInfo
    return network != null && network.isConnected
}

enum class NetworkType {
    WIFI, MOBILE, OTHER, NONE
}

fun Context.networkTypeName(): String {
    var result = "(No Network)"
    try {
        val cm = this.getConnectivityManager()
        val info = cm.activeNetworkInfo
        if (info == null || !info.isConnectedOrConnecting) {
            return result
        }
        result = info.typeName
        if (info.type == ConnectivityManager.TYPE_MOBILE) {
            result += info.subtypeName
        }
    } catch (ignored: Throwable) {
    }
    return result
}

fun Context.networkOperator(): String {
    val tm = this.telecomManager.getTelephonyManager()
    return tm.networkOperator
}

fun Context.networkType(): NetworkType {
    val cm = this.getConnectivityManager()
    val info = cm.activeNetworkInfo
    if (info == null || !info.isConnectedOrConnecting) {
        return NetworkType.NONE
    }
    val type = info.type
    if (ConnectivityManager.TYPE_WIFI == type) {
        return NetworkType.WIFI
    } else if (ConnectivityManager.TYPE_MOBILE == type) {
        return NetworkType.MOBILE
    } else {
        return NetworkType.OTHER
    }
}

fun Context.isWifi(): Boolean {
    return networkType() == NetworkType.WIFI
}

fun Context.isMobile(): Boolean {
    return networkType() == NetworkType.MOBILE
}

/*fun Context.isConnected(): Boolean {
    val cm = this.getConnectivityManager()
    val info = cm.activeNetworkInfo
    return info != null && info.isConnectedOrConnecting
}*/


// Another approach
/**
 * get network connection check
 *
 * if wifi is connected, will return 2
 * if mobile (3G, LTE) is connect, will return 1
 * else, return 0
 *
 * @return network state (check legend above)
 */
fun Context.checkNetwork(): Int {
    val info = connectivityManager?.activeNetworkInfo ?: return 0
    return when (info.type) {
        ConnectivityManager.TYPE_WIFI -> 2
        ConnectivityManager.TYPE_MOBILE -> 1
        else -> 0
    }
}

/**
 * get Wifi connection check
 */
fun Context.isWifiConnected(): Boolean = checkNetwork() == 2

/**
 * get Mobile connection check
 */
fun Context.isMobileConnected(): Boolean = checkNetwork() == 1

/**
 * get state of not connected
 */
fun Context.isNotConnected(): Boolean = isConnected().not()

/***
 * get state of connected
 */
fun Context.isConnected(): Boolean = checkNetwork() != 0