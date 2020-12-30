package com.macrohard.common_android.helper

import android.annotation.SuppressLint
import android.app.Fragment
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Build
import java.io.*
import java.util.*


/*
 * Common Utils of App
 * */
object IntentsUtil {
    /**
     * Add the [Intent.FLAG_ACTIVITY_CLEAR_TASK] flag to the [Intent].
     *
     * @return the same intent with the flag applied.
     */
    inline fun Intent.clearTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) }

    /**
     * Add the [Intent.FLAG_ACTIVITY_CLEAR_TOP] flag to the [Intent].
     *
     * @return the same intent with the flag applied.
     */
    inline fun Intent.clearTop(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) }

    /**
     * Add the [Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET] flag to the [Intent].
     *
     * @return the same intent with the flag applied.
     */
    @Deprecated(message = "Deprecated in Android", replaceWith = ReplaceWith("org.jetbrains.anko.newDocument"))
    inline fun Intent.clearWhenTaskReset(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET) }

    /**
     * Add the [Intent.FLAG_ACTIVITY_NEW_DOCUMENT] flag to the [Intent].
     *
     * @return the same intent with the flag applied.
     */
    inline fun Intent.newDocument(): Intent = apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
        } else {
            @Suppress("DEPRECATION")
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        }
    }

    /**
     * Add the [Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS] flag to the [Intent].
     *
     * @return the same intent with the flag applied.
     */
    inline fun Intent.excludeFromRecents(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS) }

    /**
     * Add the [Intent.FLAG_ACTIVITY_MULTIPLE_TASK] flag to the [Intent].
     *
     * @return the same intent with the flag applied.
     */
    inline fun Intent.multipleTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK) }

    /**
     * Add the [Intent.FLAG_ACTIVITY_NEW_TASK] flag to the [Intent].
     *
     * @return the same intent with the flag applied.
     */
    inline fun Intent.newTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }

    /**
     * Add the [Intent.FLAG_ACTIVITY_NO_ANIMATION] flag to the [Intent].
     *
     * @return the same intent with the flag applied.
     */
    inline fun Intent.noAnimation(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) }

    /**
     * Add the [Intent.FLAG_ACTIVITY_NO_HISTORY] flag to the [Intent].
     *
     * @return the same intent with the flag applied.
     */
    inline fun Intent.noHistory(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY) }

    /**
     * Add the [Intent.FLAG_ACTIVITY_SINGLE_TOP] flag to the [Intent].
     *
     * @return the same intent with the flag applied.
     */
    inline fun Intent.singleTop(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP) }

    fun Context.browse(url: String, newTask: Boolean = false): Boolean {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            if (newTask) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            return true
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            return false
        }
    }

    fun Context.share(text: String, subject: String = "", title: String? = null): Boolean {
        return try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(android.content.Intent.EXTRA_TEXT, text)
            startActivity(Intent.createChooser(intent, title))
            true
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            false
        }
    }

    fun Context.email(email: String, subject: String = "", text: String = ""): Boolean {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        if (subject.isNotEmpty())
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        if (text.isNotEmpty())
            intent.putExtra(Intent.EXTRA_TEXT, text)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            return true
        }
        return false

    }


    /**
     * Make phone call with given phone number
     *
     * this feature need CALL_PHONE permission.
     *
     * @param[number] Phone number to call
     */
    @SuppressLint("MissingPermission")
    fun Context.makeCall(number: String): Boolean {
        return try {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
            startActivity(intent)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Make phone dial with given phone number
     *
     * @param[number] Phone number to dial
     */
    fun Context.dialCall(number: String): Boolean {
        return try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
            startActivity(intent)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun Context.sendSMS(number: String, text: String = ""): Boolean {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$number"))
            intent.putExtra("sms_body", text)
            startActivity(intent)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}
