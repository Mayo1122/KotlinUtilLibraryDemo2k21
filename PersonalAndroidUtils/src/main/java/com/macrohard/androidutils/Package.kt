package com.macrohard.androidutils

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature

/**
 * User: mcxiaoke
 * Date: 16/1/27
 * Time: 11:33
 */


fun Context.isAppInstalled(packageName: String): Boolean {
    try {
        return this.packageManager.getPackageInfo(packageName, 0) != null
    } catch (e: PackageManager.NameNotFoundException) {
        return false
    }
}

fun Context.isAppEnabled(packageName: String): Boolean {
    return try {
        packageManager.getApplicationInfo(packageName, 0)?.enabled ?: false
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun Context.isMainProcess(): Boolean {
    val am: ActivityManager = this.getActivityManager()
    val processes = am.runningAppProcesses
    val mainProcessName = this.packageName
    val myPid = android.os.Process.myPid()
    return processes.any { p ->
        p.pid == myPid
                && mainProcessName == p.processName
    }
}

fun Context.isComponentDisabled(clazz: Class<*>): Boolean {
    val componentName = ComponentName(this, clazz)
    val pm = this.packageManager
    return pm.getComponentEnabledSetting(componentName) ==
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED
}

fun Context.isComponentEnabled(clazz: Class<*>): Boolean {
    val componentName = ComponentName(this, clazz)
    val pm = this.packageManager
    return pm.getComponentEnabledSetting(componentName) !=
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED
}

fun Context.enableComponent(clazz: Class<*>) {
    return setComponentState(clazz, true)
}

fun Context.disableComponent(clazz: Class<*>) {
    return setComponentState(clazz, false)
}

fun Context.setComponentState(clazz: Class<*>, enable: Boolean) {
    val componentName = ComponentName(this, clazz)
    val pm = this.packageManager
    val oldState = pm.getComponentEnabledSetting(componentName)
    val newState = if (enable)
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    else
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED
    if (newState != oldState) {
        val flags = PackageManager.DONT_KILL_APP
        pm.setComponentEnabledSetting(componentName, newState, flags)
    }
}


fun Context.getPackageSignature(): Signature? {
    val pm = this.packageManager
    var info: PackageInfo? = null
    try {
        info = pm.getPackageInfo(this.packageName, PackageManager.GET_SIGNATURES)
    } catch (ignored: Exception) {
    }
    return info?.signatures?.get(0)
}
