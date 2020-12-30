@file:JvmName("RichUtils")
@file:JvmMultifileClass

package com.macrohard.androidutils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.macrohard.androidutils.telephonyManager

/**
 * get androidId of device
 *
 * @return androidId of device
 */
@SuppressLint("HardwareIds")
fun Context.getAndroidId(): String = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID) ?: ""
