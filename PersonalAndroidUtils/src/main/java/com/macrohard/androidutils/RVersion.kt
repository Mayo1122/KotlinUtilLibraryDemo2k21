package com.macrohard.androidutils

import android.content.Context

/**
 * Created by costular on 14/08/17.
 */
fun Context.getVersionCode(): Int = packageManager.getPackageInfo(packageName, 0).versionCode

fun Context.getVersionName(): String = packageManager.getPackageInfo(packageName, 0).versionName