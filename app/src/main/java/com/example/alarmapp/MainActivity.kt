    package com.example.alarmapp

import android.app.*
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.alarmapp.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_12H

    class MainActivity : AppCompatActivity() {
        lateinit var calendar:Calendar

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        binding.timeshow.setOnClickListener {
            var picker = MaterialTimePicker.Builder()
                .setTimeFormat(CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build()
            picker.show(supportFragmentManager,"Alarm")
            picker.addOnPositiveButtonClickListener {
                if(picker.hour>=12)
                {
                    binding.timeshow.text=String.format("%02d",picker.hour-12) + ":"+String.format("%02d",picker.minute) + "PM"
                }
                else
                {
                    binding.timeshow.text=String.format("%02d",picker.hour) + ":"+String.format("%02d",picker.minute) + "AM"
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    calendar = Calendar.getInstance()
                    calendar[Calendar.HOUR_OF_DAY] = picker.hour
                    calendar[Calendar.MINUTE] = picker.minute
                    calendar[Calendar.SECOND] = 0
                    calendar[Calendar.MILLISECOND] = 0
                }
            }


        }
        binding.button.setOnClickListener {
//            var alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
//            val intent = Intent(this,AlarmReceiver::class.java)
//            var pendingIntent=PendingIntent.getBroadcast(this,0,intent,0)
//            alarmManager.setRepeating(
//                AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
//                AlarmManager.INTERVAL_DAY,pendingIntent
//            )
            val intent = Intent(this,AlarmReceiver::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            }

        }

        setContentView(binding.root)
        createNotificationChannel()
    }

        private fun createNotificationChannel() {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            {
                val name :CharSequence="alarm"
                val description="Channel For Alarm Manager"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel("Alarm",name,importance)
                channel.description=description
                val notificationManager=getSystemService(
                    NotificationManager::class.java
                )
                notificationManager.createNotificationChannel(channel)
            }
        }
    }