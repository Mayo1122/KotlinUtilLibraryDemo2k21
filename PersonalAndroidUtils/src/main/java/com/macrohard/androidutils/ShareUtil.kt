package com.macrohard.common_android.helper

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.text.Spanned
import java.io.*
import java.util.*


/*
 * Common Utils of App
 * */
object ShareUtil {


    fun shareWithWhatsApp(context: Context, promo: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, promo)
        sendIntent.type = "text/plain"
        sendIntent.setPackage("com.whatsapp")
        try {
            context.startActivity(sendIntent)
        } catch (ex: ActivityNotFoundException) {
            print("")
        }

    }

    fun contactViaWhatsApp(context: Context, number: String) {

        val uri = Uri.parse("smsto:$number")
        val sendIntent = Intent(Intent.ACTION_SENDTO, uri)
        sendIntent.putExtra("sms_body", "")
        sendIntent.putExtra("chat", true)
        sendIntent.setPackage("com.whatsapp")
        try {
            context.startActivity(sendIntent)
        } catch (ex: ActivityNotFoundException) {
            print("")
        }

    }

    fun shareWithSMS(context: Context, promo: String) {
        val sendIntent = Intent(Intent.ACTION_VIEW)
        sendIntent.data = Uri.parse("sms:")
        sendIntent.putExtra("sms_body", promo)
        try {
            context.startActivity(sendIntent)
        } catch (ex: ActivityNotFoundException) {
            print("")
        }

    }


    fun shareWithEmail(context: Context, email: String, subject: String, body: String) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        //        i.setType("text/html");
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        i.putExtra(Intent.EXTRA_SUBJECT, subject)
        i.putExtra(Intent.EXTRA_TEXT, body)
        try {
            context.startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            print("")
        }

    }

    fun shareWithEmail(context: Context, email: String, subject: String, body: Spanned) {
        val i = Intent(Intent.ACTION_SEND)
        //        i.setType("message/rfc822");
        i.type = "text/html"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        i.putExtra(Intent.EXTRA_SUBJECT, subject)
        i.putExtra(Intent.EXTRA_TEXT, body)
        try {
            context.startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            print("")
        }

    }

    fun shareWithEmail(context: Context, email: String, secondEmail: String, subject: String, body: String) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(email, secondEmail))
        i.putExtra(Intent.EXTRA_SUBJECT, subject)
        i.putExtra(Intent.EXTRA_TEXT, body)
        try {
            context.startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            print("")
        }

    }

    fun callNumber(context: Context, phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.setData(Uri.parse("tel:" + phoneNumber));

        try {
            context.startActivity(callIntent)
        } catch (ex: ActivityNotFoundException) {
            print("")
        }
    }

    fun openLink(context: Context, url: String) {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        try {
            context.startActivity(Intent.createChooser(intent, "Choose browser")) // Choose browser is arbitrary :)
        } catch (ex: ActivityNotFoundException) {
            print("")
        }
    }

    fun contactViaEmail(context: Context, email: String) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        i.putExtra(Intent.EXTRA_SUBJECT, "Avant Feedback")
        i.putExtra(Intent.EXTRA_TEXT, "")
        try {
            context.startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            print("")
        }

    }

    fun contactViaSMS(context: Context, number: String) {
        val sendIntent = Intent(Intent.ACTION_VIEW)
        sendIntent.data = Uri.parse("sms:")
        sendIntent.putExtra("sms_body", "")
        try {
            context.startActivity(sendIntent)
        } catch (ex: ActivityNotFoundException) {
            print("")
        }

    }

    fun shareWithFacebook(context: Context, promo: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/426253597411506"))
            context.startActivity(intent)
        } catch (e: Exception) {
            try {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/appetizerandroid")))
            } catch (e1: ActivityNotFoundException) {
                print("")
            }

        }

    }

    fun emailSendingIntent(context: Context, message: String) {

        val email = Intent(Intent.ACTION_SEND)
        //        email.putExtra(Intent.EXTRA_EMAIL, new String[]{AppPreferences.getSettings().getSettings().getCustomer_support_email()});
        email.putExtra(Intent.EXTRA_SUBJECT, "Support query")
        email.putExtra(Intent.EXTRA_TEXT, message)
        context.startActivity(Intent.createChooser(email, "Choose an Email app :"))
    }

    fun callingIntent(context: Context, number: String) {

        try {
            val callingIntent = Intent(Intent.ACTION_VIEW)
            callingIntent.data = Uri.parse("tel:$number")
            context.startActivity(callingIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun callingIntentActionCall(context: Context, number: String) {

        try {
            val callingIntent = Intent(Intent.ACTION_VIEW)
            callingIntent.data = Uri.parse("tel:$number")
            //            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) !=
            //                    PackageManager.PERMISSION_GRANTED) {
            //                // TODO: Consider calling
            //                //    ActivityCompat#requestPermissions
            //                // here to request the missing permissions, and then overriding
            //                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                //                                          int[] grantResults)
            //                // to handle the case where the user grants the permission. See the documentation
            //                // for ActivityCompat#requestPermissions for more details.
            //                return;
            //            }
            context.startActivity(callingIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun callingIntentWithoutPlus(context: Context, number: String) {

        try {
            val callingIntent = Intent(Intent.ACTION_VIEW)
            callingIntent.data = Uri.parse("tel:$number")
            context.startActivity(callingIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
