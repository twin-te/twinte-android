package net.twinte.android.work

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import net.twinte.android.MainActivity
import net.twinte.android.R
import net.twinte.android.SettingsActivity
import net.twinte.android.TWINTE_DEBUG
import net.twinte.android.datastore.schedule.ScheduleDataStore
import net.twinte.android.datastore.schedulenotification.ScheduleNotificationDataStore
import net.twinte.android.model.Day
import java.util.Calendar
import javax.inject.Inject

/**
 * 特殊日程通知を管理する
 */
@AndroidEntryPoint
class ScheduleNotifier : BroadcastReceiver() {
    @Inject
    lateinit var scheduleDataStore: ScheduleDataStore

    override fun onReceive(context: Context, intent: Intent?) = runBlocking {
        Log.d("ScheduleNotifier", "Received Broadcast")
        try {
            val targetDate = Calendar.getInstance().apply {
                if (get(Calendar.HOUR_OF_DAY) > 18) add(Calendar.DATE, 1)
            }

            val schedule = scheduleDataStore.getSchedule(targetDate.time)

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

        // 以下の if 式は自動生成されている
        // TODO: API Level 33 以降向けに通知許可を得るための処理を追加する
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify(1, notification)
    }

    @AndroidEntryPoint
    class OnBootCompleteOrPackageReplaced @Inject constructor() : BroadcastReceiver() {
        @Inject
        lateinit var scheduleNotificationDataStore: ScheduleNotificationDataStore

        override fun onReceive(context: Context, intent: Intent?) {
            Log.d("ScheduleNotifier", "onReceived ${intent?.action}")
            scheduleNotificationDataStore.schedule()
        }
    }
}
