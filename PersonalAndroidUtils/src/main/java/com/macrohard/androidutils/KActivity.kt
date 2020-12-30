
package com.macrohard.androidutils

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Activity.setFullScreen() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Activity.showToolbar() {
    actionBar?.show()
}

fun Activity.hideToolbar() {
    actionBar?.hide()
}

fun AppCompatActivity.showToolbar() {
    supportActionBar?.show()
}

fun AppCompatActivity.hideToolbar() {
    supportActionBar?.hide()
}

inline fun Activity.lockCurrentScreenOrientation() {
    requestedOrientation = when (resources.configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        else -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
    }
}

inline fun Activity.unlockScreenOrientation() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
}

fun Activity.getActivity(): Activity = this

fun View.getActivity(): Activity = context as Activity

fun Fragment.finish() = activity?.finish()

fun Activity.restart() {
    val intent = this.intent
    this.overridePendingTransition(0, 0)
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
    this.finish()
    this.overridePendingTransition(0, 0)
    this.startActivity(intent)
}