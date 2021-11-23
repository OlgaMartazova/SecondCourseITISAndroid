package com.itis.secondcourseitis

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itis.secondcourseitis.databinding.ActivityClockBinding
import java.util.*

class ClockActivity: AppCompatActivity() {
    private lateinit var binding: ActivityClockBinding
    private var service: NotificationService? = null
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        service = NotificationService(this)

        //похоже не работает(
        val receiver = ComponentName(applicationContext, BootReceiver::class.java)

        applicationContext.packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        var hour: String
        var minute: String

        binding.btnStart.setOnClickListener {
            hour = binding.etHour.text.toString()
            minute = binding.etMinute.text.toString()
            setTime(hour, minute)
            setClock(this)
        }

        binding.btnStop.setOnClickListener {
//            intent = Intent(this, Receiver::class.java)
//            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
            alarmManager.cancel(pendingIntent)
        }
    }

    private fun setTime(hour: String, minute: String) {
        calendar = Calendar.getInstance()
        if (hour != "") {
            calendar.set(Calendar.HOUR_OF_DAY, hour.toInt())
        }
        if (minute != "") {
            calendar.set(Calendar.MINUTE, minute.toInt())
        }
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        service = null
    }

    fun setClock(context: Context) {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        intent = Intent(this, Receiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}