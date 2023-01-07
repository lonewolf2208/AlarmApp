package com.example.alarmapp

import android.R
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.concurrent.timerTask


class AlarmReceiver: Service() {

    @SuppressLint("RemoteViewLayout", "WrongConstant")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            while (true) {
                Log.e("Service", "Service is running...")
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()

        Timer().schedule(timerTask {
            ServiceBuilder.buildService().getDetails().enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    Log.d("sda",response.toString())
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.d("sda",t.message.toString())
                }
            })
        },10000)
        val remoteViews = RemoteViews(
            applicationContext.packageName,
           com.example.alarmapp.R.layout.asda
        )

        val CHANNELID = "Foreground Service ID"
        val channel = NotificationChannel(
            CHANNELID,
            CHANNELID,
            NotificationManager.IMPORTANCE_LOW
        )

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification: Notification.Builder = Notification.Builder(this, CHANNELID)
            .setContentText("Service is running")
            .setCustomContentView(remoteViews)
            .setContentTitle("Service enabled")
            .setSmallIcon(R.color.transparent)
        startForeground(1, notification.build())
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}