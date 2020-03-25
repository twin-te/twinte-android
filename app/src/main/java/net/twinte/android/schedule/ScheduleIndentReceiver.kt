package net.twinte.android.schedule

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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
                .registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
                    if (key != "enable_schedule_notification" && key != "notification_timing") return@registerOnSharedPreferenceChangeListener
                    val notificationEnabled = sharedPreferences.getBoolean("enable_schedule_notification", false)
                    val timing = sharedPreferences.getStringSet("notification_timing", emptySet())
                    val alarmManager = getSystemService(AlarmManager::class.java)!!

                    val pendingIntent =
                        PendingIntent.getBroadcast(
                            this,
                            0,
                            Intent(this, ScheduleIndentReceiver::class.java).apply { action = "update" },
                            0
                        )

                    alarmManager.cancel(pendingIntent)

                    if (notificationEnabled)
                        timing?.forEach {
                            val calendar: Calendar = Calendar.getInstance().apply {
                                var time = it.toInt()
                                if (time < 0) time += 24
                                timeInMillis = System.currentTimeMillis()
                                set(Calendar.HOUR_OF_DAY, it.toInt())
                                set(Calendar.MINUTE, 1)
                            }
                            alarmManager.setRepeating(
                                AlarmManager.RTC_WAKEUP,
                                calendar.timeInMillis,
                                1000 * 60 * 60 * 24,
                                pendingIntent
                            )
                        }
                }
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { context ->
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
