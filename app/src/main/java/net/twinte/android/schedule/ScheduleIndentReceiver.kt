package net.twinte.android.schedule

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.preference.PreferenceManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.twinte.android.MainActivity
import net.twinte.android.Network
import net.twinte.android.R
import net.twinte.android.widget.TimetableWidget
import net.twinte.android.widget.addDay
import java.util.*

class ScheduleIndentReceiver : BroadcastReceiver() {

    companion object {

        private fun getPendingIntent(context: Context): PendingIntent =
            PendingIntent.getBroadcast(
                context,
                0,
                Intent(context, ScheduleIndentReceiver::class.java).apply { action = "update" },
                0
            )

        private fun Context.getListener() = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key != "enable_schedule_notification" && key != "notification_timing") return@OnSharedPreferenceChangeListener
            resetAlarm()
            setNextAlarm()
        }

        fun enable(context: Context) = context.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = getString(R.string.channel_name)
                val descriptionText = getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel("schedule", name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager = getSystemService(NotificationManager::class.java)!!
                notificationManager.createNotificationChannel(channel)
            }
            PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(getListener())
        }

        private fun Context.resetAlarm() {
            val alarmManager = getSystemService(AlarmManager::class.java)!!
            alarmManager.cancel(getPendingIntent(this))
        }

        fun Context.setNextAlarm() {
            val cal = getNextAlarmDate()?.let {
                setAlarm(it)
            }
        }

        private fun Context.getNextAlarmDate(): Calendar? {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val notificationEnabled = sharedPreferences.getBoolean("enable_schedule_notification", false)
            val timing = sharedPreferences.getStringSet("notification_timing", emptySet())!!.map { it.toInt() }.sorted()
            return if (timing.isNotEmpty()) {
                val timingInToday = timing.find {
                    Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, it)
                        set(Calendar.MINUTE, 0)
                    }.after(Calendar.getInstance())
                }
                if (timingInToday != null)
                    Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, timingInToday)
                        set(Calendar.MINUTE, 0)
                    }
                else Calendar.getInstance().apply {
                    add(Calendar.DATE, 1)
                    set(Calendar.HOUR_OF_DAY, timing.first())
                    set(Calendar.MINUTE, 0)
                }
            } else null
        }

        private fun Context.setAlarm(calendar: Calendar) {
            val alarmManager = getSystemService(AlarmManager::class.java)!!
            alarmManager.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                getPendingIntent(this)
            )
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { context ->
            context.setNextAlarm()
            GlobalScope.launch {
                val date =
                    if (Calendar.getInstance().get(Calendar.HOUR) < 18) TimetableWidget.simpleDateFormat.format(Date())
                    else TimetableWidget.simpleDateFormat.format(Date()).addDay(1)

                val calendar = Network.fetchCalender(date)
                val builder = NotificationCompat.Builder(context, "schedule")
                    .setSmallIcon(R.drawable.ic_icon)
                    .setAutoCancel(true)
                    .setContentIntent(
                        PendingIntent.getActivity(
                            context,
                            0,
                            Intent(context, MainActivity::class.java), 0
                        )
                    )

                if (calendar.substituteDay != null) {
                    val notification = builder
                        .setContentText("明日は${calendar.substituteDay.change_to}曜日課です")
                        .build()
                    NotificationManagerCompat.from(context).notify(1, notification)
                } else {
                    val notification = builder
                        .setContentText("明日は通常日課です")
                        .build()
                    NotificationManagerCompat.from(context).notify(1, notification)
                }
            }
        }
    }
}
