package com.macrohard.common_android.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import com.macrohard.androidutils.notificationManager


/*
 * Common Utils of App
 * */
object NotificationsUtil {

    /**
     * this method is used to get channel id for chat notification
     */
    fun getChatNotificationChannelId(context: Context): String {
        var chanelId = "CHAT_NOTIFICATION"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val channelId = "bidsquad_chat_notification_channel_id"
            val channelName = "bidsquad_chat_notification_channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(channelId, channelName, importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager.createNotificationChannel(notificationChannel)
            chanelId = notificationChannel.id
        }
        return chanelId
    }

    /**
     * this method is used to get channel id for chat notification
     */
    fun getPushNotificationChannelId(context: Context): String {
        var chanelId = "PUSH_NOTIFICATION"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val channelId = "bidsquad_chat_notification_channel_id"
            val channelName = "bidsquad_chat_notification_channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(channelId, channelName, importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager.createNotificationChannel(notificationChannel)
            chanelId = notificationChannel.id
        }
        return chanelId
    }

    /**
     * create Notification Channel for Android Oreo and above.
     * every options is optional, if you doesn't matter whatever value,
     * leave them no parameters.
     *
     * @param[id] channel id, if this value is not present, it will be package name
     * @param[name] channel name, if this value is not present, it will be app name
     * @param[description] channel description, if this value is not present, it will be app name
     * @param[importance] importance of channel, if this value is not present, it will be IMPORTANCE_LOW
     * @return generated channel id
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @JvmOverloads
    fun Context.createNotificationChannel(id: String = "", name: String = "", description: String = "", importance: Int = NotificationManager.IMPORTANCE_HIGH): String {
        if (Build.VERSION.SDK_INT < 26) {
            return ""
        }

        val newId = id.isEmptyOrReturn(this.packageName)
        val appName = if (applicationInfo.labelRes != 0) getString(applicationInfo.labelRes) else applicationInfo.nonLocalizedLabel.toString()
        val newName = name.isEmptyOrReturn(appName)
        val newDescription = description.isEmptyOrReturn(appName)

        val notificationManager = this.notificationManager
        val mChannel = NotificationChannel(newId, newName, importance)
        mChannel.description = newDescription
        notificationManager.createNotificationChannel(mChannel)

        return newId
    }
}
