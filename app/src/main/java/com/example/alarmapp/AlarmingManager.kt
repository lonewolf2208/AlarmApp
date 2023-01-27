package com.example.alarmapp

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class AlarmingManager: BroadcastReceiver() {
   lateinit var pendingIntent:PendingIntent
    override fun onReceive(context: Context?, intent: Intent?) {
            val i =Intent(context,MainActivity::class.java)
            i!!.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity(
                context,
                0,
                i,
                PendingIntent.FLAG_MUTABLE
            )
        }
        else
        {
            pendingIntent = PendingIntent.getActivity(
                context,
                0,
                i,
                0
            )
        }
        val builder=NotificationCompat.Builder(context!!,"Alarm")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("DSAda")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
        val notificationManager=NotificationManagerCompat.from(context)
        notificationManager.notify(123,builder.build())
        Log.d("sada","Alarm RInging")
    }
}