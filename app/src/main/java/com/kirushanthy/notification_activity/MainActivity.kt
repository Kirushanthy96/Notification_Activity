package com.kirushanthy.notification_activity

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    private var notificationManager:NotificationManager?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager

        createNotificationChannel(id="com.kirushanthy.notification_activity",name = "notification",description = "hi kiru")
    }

    @SuppressLint("NewApi")
    private fun createNotificationChannel(id:String, name:String, description:String) {

        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id, name, importance)
        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

        notificationManager?.createNotificationChannel(channel)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendNotification(view: View){

        val notificationId=101
        val channelId="com.kirushanthy.notification_activity"

        val notification=Notification.Builder(this@MainActivity,channelId)
            .setContentTitle("Notification")
            .setContentText("Hi Kirushanthy")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setChannelId(channelId)
            .build()

        notificationManager?.notify(notificationId,notification)
    }


}
