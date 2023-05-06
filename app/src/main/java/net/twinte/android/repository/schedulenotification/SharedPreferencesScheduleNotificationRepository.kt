package net.twinte.android.repository.schedulenotification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import net.twinte.android.work.ScheduleNotifier
import java.util.Calendar
import javax.inject.Inject

class SharedPreferencesScheduleNotificationRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) : ScheduleNotificationRepository {
    private val preferenceChangeListener by lazy {
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key != "enable_schedule_notification" && key != "notification_timing") return@OnSharedPreferenceChangeListener
            schedule()
        }
    }

    override fun schedule() {
        cancelAll()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences
            .registerOnSharedPreferenceChangeListener(preferenceChangeListener)
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

            scheduleAt(notifyDate.timeInMillis, hour)
        }
    }

    private fun cancelAll() {
        val alarmManager = context.getSystemService(AlarmManager::class.java)
        for (requestCode in 0..24) {
            alarmManager.cancel(alarmIntent(requestCode))
        }
        Log.d("ScheduleNotifier", "All schedule canceled")
    }

    private fun scheduleAt(timeInMillis: Long, requestCode: Int) {
        val alarmManager = context.getSystemService(AlarmManager::class.java)
        // 指定された時刻付近で１日おきに設定
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            1000 * 60 * 60 * 24,
            alarmIntent(requestCode),
        )
        Log.d("ScheduleNotifier", "Scheduled at $timeInMillis $requestCode")
    }

    private fun alarmIntent(requestCode: Int) =
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
