package net.twinte.android.work

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.preference.PreferenceManager
import kotlinx.coroutines.runBlocking
import net.twinte.android.MainActivity
import net.twinte.android.R
import net.twinte.android.SettingsActivity
import net.twinte.android.TWINTE_DEBUG
import net.twinte.android.model.Day
import net.twinte.android.repository.ScheduleRepository
import java.util.Calendar

/**
 * 特殊日程通知を管理する
 */
class ScheduleNotifier : BroadcastReceiver() {
    companion object {
        private var _preferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener? = null
        private fun getPreferenceChangeListener(context: Context): SharedPreferences.OnSharedPreferenceChangeListener {
            if (_preferenceChangeListener == null) {
                _preferenceChangeListener =
                    SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
                        if (key != "enable_schedule_notification" && key != "notification_timing") return@OnSharedPreferenceChangeListener
                        schedule(context)
                    }
            }
            return _preferenceChangeListener!!
        }

        fun schedule(context: Context) {
            cancelAll(context)
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            sharedPreferences
                .registerOnSharedPreferenceChangeListener(getPreferenceChangeListener(context))
            if (!sharedPreferences.getBoolean("enable_schedule_notification", true)) return

            val timing = sharedPreferences.getStringSet("notification_timing", setOf("21", "6"))?.map { it.toInt() }?.sorted()
                ?: emptyList()

            timing.forEach { hour ->
                val currentDate = Calendar.getInstance()
                val notifyDate = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, hour)
                    set(Calendar.MINUTE, 0)

                    if (before(currentDate)) {
                        add(Calendar.HOUR_OF_DAY, 24)
                    }
                }

                scheduleAt(context, notifyDate.timeInMillis, hour)
            }
        }

        private fun cancelAll(context: Context) {
            val alarmManager = context.getSystemService(AlarmManager::class.java)
            for (requestCode in 0..24) {
                alarmManager.cancel(alarmIntent(context, requestCode))
            }
            Log.d("ScheduleNotifier", "All schedule canceled")
        }

        private fun scheduleAt(context: Context, timeInMillis: Long, requestCode: Int) {
            val alarmManager = context.getSystemService(AlarmManager::class.java)
            // 指定された時刻付近で１日おきに設定
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                1000 * 60 * 60 * 24,
                alarmIntent(context, requestCode),
            )
            Log.d("ScheduleNotifier", "Scheduled at $timeInMillis $requestCode")
        }

        private fun alarmIntent(context: Context, requestCode: Int) =
            Intent(context, ScheduleNotifier::class.java).let { intent ->
                intent.action = "net.twinte.android.action.ScheduleNotifier"
                PendingIntent.getBroadcast(
                    context,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE,
                )
            }
    }

    override fun onReceive(context: Context, intent: Intent?) = runBlocking {
        Log.d("ScheduleNotifier", "Received Broadcast")
        try {
            val targetDate = Calendar.getInstance().apply {
                if (get(Calendar.HOUR_OF_DAY) > 18) add(Calendar.DATE, 1)
            }

            val schedule = ScheduleRepository(context).getSchedule(targetDate.time)

            val substitute = schedule.events.find { it.changeTo != null }?.changeTo
            if (substitute != null) {
                createSubstituteDayNotification(context, substitute)
            } else if (TWINTE_DEBUG) {
                createNotification(context, "[Debug]明日は通常日課です", "${schedule.date} ${schedule.module?.module?.m}")
            }
        } catch (e: Throwable) {
            // TODO エラー処理
            Log.d("ScheduleNotifier", "$e")
            Unit
        }
    }

    private fun createSubstituteDayNotification(context: Context, day: Day) {
        val label = if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 18) "明日" else "今日"
        val title = "${label}は${day.d}曜日課です"
        val text = "日程はウィジットで確認できます"
        createNotification(context, title, text)
    }

    private fun createNotification(context: Context, title: String, text: String) = with(context) {
        val name = getString(R.string.schedule_notify_channel_name)
        val descriptionText = getString(R.string.schedule_notify_channel_description)
        val importance = NotificationManagerCompat.IMPORTANCE_DEFAULT
        val channel = NotificationChannelCompat.Builder(context.getString(R.string.schedule_notify_channel_id), importance)
            .setName(name)
            .setDescription(descriptionText)
            .build()
        // Register the channel with the system
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(context, getString(R.string.schedule_notify_channel_id))
            .setSmallIcon(R.drawable.ic_icon)
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    0,
                    Intent(context, MainActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE,
                ),
            ).addAction(
                R.drawable.ic_icon,
                "通知設定",
                PendingIntent.getActivity(
                    context,
                    0,
                    Intent(context, SettingsActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE,
                ),
            ).setContentTitle(title)
            .setContentText(text)
            .setChannelId(context.getString(R.string.schedule_notify_channel_id))
            .build()
        notificationManager.notify(1, notification)
    }

    class OnBootCompleteOrPackageReplaced : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            Log.d("ScheduleNotifier", "onReceived ${intent?.action}")
            schedule(context)
        }
    }
}
