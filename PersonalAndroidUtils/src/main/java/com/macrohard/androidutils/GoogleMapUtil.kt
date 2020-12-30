package com.macrohard.common_android.helper

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import java.util.*


/*
 * Common Utils of App
 * */
object GoogleMapUtil {

    fun openLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try {
            context.startActivity(Intent.createChooser(intent, "Choose browser")) // Choose browser is arbitrary :)
        } catch (ex: ActivityNotFoundException) {
            ex.printStackTrace()
        }
    }



    fun openMap(context: Context, location: MutableList<Double>){
        location[0] = 33.729566625544116
        location[1] = 73.03730380079827

        val uri = java.lang.String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", location[0], location[1])
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))

        if (intent.resolveActivity(context.packageManager)!=null){
            context.startActivity(intent)
        }else{
            print("Sorry, No app can open map.")
        }
    }


    fun openGoogleMap(context: Context, location: MutableList<Double>) {

        location[0] = 33.729566625544116
        location[1] = 73.03730380079827

        val locationString = "geo:0,0?q="+location[0]+","+location[1]+"(Google+Sydney)"
        //val locationString = "geo:"+location[0]+","+location[1]+"?z=15" //"geo:37.7749,-122.4194"
        val gmmIntentUri = Uri.parse(locationString)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        try {
            context.startActivity(mapIntent)
        } catch (ex: ActivityNotFoundException) {
            print("Error in google map opening")
        }
    }

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
