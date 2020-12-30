package com.macrohard.common_android.helper

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import androidx.appcompat.widget.AppCompatImageView
import java.util.*


/*
 * Common Utils of App
 * */
object MustUtil {

    /**
     * This function can load image on ImageView
     * @param imageView on which image will be show
     * @param link link of image getting from server
     */
    fun loadImgURL(imageView: AppCompatImageView, link: String) {
        /*Picasso.get().load(link)
                .fit().centerInside()
                .into(imageView)*/
    }

    fun rateApp(context: Context) {
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.packageName)))
        } catch (e1: ActivityNotFoundException) {
            try {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + context.packageName)))
            } catch (e2: ActivityNotFoundException) {
                print("Error")
            }

        }

    }

    fun updateLanguage(context: Activity, langCode: String) {

        val config = context.resources.configuration
        var sysLocale: Locale? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = config.locales.get(0)
        } else {
            sysLocale = config.locale
        }
        if (langCode != "" && !sysLocale!!.language.equals(langCode, ignoreCase = true)) {
            val locale = Locale(langCode)
            Locale.setDefault(locale)
            //            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //                config.setLocale(locale);
            ////                setSystemLocale(config, locale);
            //            } else {
            config.locale = locale
            //                setSystemLocaleLegacy(config, locale);
            //            }
            //            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //                context = context.createConfigurationContext(config);
            //            } else {
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
            //            }
            //            ActivityStackManager.getInstance(context).reLaunchApp(context);

        }

    }


    fun getOrientation(activity: Activity, photoUri: Uri): Int {
        /* it's on the external media. */
        val value: Int

        val cursor = activity.contentResolver.query(photoUri,
                arrayOf(MediaStore.Images.ImageColumns.ORIENTATION), null, null, null)

        if (cursor!!.count == 0) {
            return -1
        }
        cursor.moveToFirst()
        value = cursor.getInt(0)
        cursor.close()
        return value
    }


    fun getDeviceId(context: Context): String {
        val androidId = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID)
        return androidId
    }
}
