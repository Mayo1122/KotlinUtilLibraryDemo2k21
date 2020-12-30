package com.macrohard.common_android.helper

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.text.TextUtils


/*
 * Common Utils of App
 * */
object LocationUtil {

    /*fun checkGooglePlayServicesVersion(activity: Activity) {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(activity)
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show()
            }
            redLog("GooglePlayService", "false")
        } else {
            redLog("GooglePlayService", "true")
        }
    }*/

    fun isLocationEnabled(context: Context): Boolean {
        var locationMode = 0
        val locationProviders: String

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.contentResolver, Settings.Secure.LOCATION_MODE)
            } catch (e: Settings.SettingNotFoundException) {
                e.printStackTrace()
                return false
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF
        } else {
            locationProviders = Settings.Secure.getString(context.contentResolver, Settings.Secure.LOCATION_PROVIDERS_ALLOWED)
            return !TextUtils.isEmpty(locationProviders)
        }
    }
}
