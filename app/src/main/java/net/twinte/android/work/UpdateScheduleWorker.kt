package net.twinte.android.work

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest.Companion.MIN_BACKOFF_MILLIS
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import net.twinte.android.MainActivity
import net.twinte.android.R
import net.twinte.android.TWINTE_DEBUG
import net.twinte.android.datastore.schedule.ScheduleDataStore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * 一日一回、APIサーバーからウィジットに必要なデータを取得するJobの管理を行う
 */
@HiltWorker
class UpdateScheduleWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {

    @Inject
    lateinit var scheduleDataStore: ScheduleDataStore

    @Inject
    lateinit var workManager: WorkManager

    companion object {
        private const val TAG = "UPDATE_SCHEDULE"
        fun scheduleNextUpdate(workManager: WorkManager) {
            val currentDate = Calendar.getInstance()

            // Set Execution around 18:00 ~ 18:30
            val dueDate = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 18)
                set(Calendar.MINUTE, (Math.random() * 30).toInt())
                set(Calendar.SECOND, (Math.random() * 59).toInt())
            }

            if (dueDate.before(currentDate)) {
                dueDate.add(Calendar.HOUR_OF_DAY, 24)
            }
            val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis

            val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            val updateScheduleWorkRequest = OneTimeWorkRequestBuilder<UpdateScheduleWorker>()
                .setConstraints(constraints)
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS,
                )
                .addTag(TAG).build()
            workManager.enqueueUniqueWork(TAG, ExistingWorkPolicy.REPLACE, updateScheduleWorkRequest)
            Log.d(
                "UpdateScheduleWorker",
                "work enqueued at ${SimpleDateFormat.getDateTimeInstance().format(dueDate.time)}",
            )
        }
    }

    override suspend fun doWork() = try {
        scheduleDataStore.update()
        scheduleNextUpdate(workManager)
        Log.d("UpdateScheduleWorker", "work success")
        if (TWINTE_DEBUG) {
            debugNotification("success")
        }
        Result.success()
    } catch (e: Throwable) {
        Log.d("UpdateScheduleWorker", "work failure $e")
        if (TWINTE_DEBUG) {
            debugNotification("$e")
        }
        Result.retry()
    }

    private fun debugNotification(msg: String) {
        val notificationManager = NotificationManagerCompat.from(applicationContext)

        val notification = NotificationCompat.Builder(applicationContext, applicationContext.getString(R.string.schedule_notify_channel_id))
            .setSmallIcon(R.drawable.ic_icon)
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    applicationContext,
                    1,
                    Intent(applicationContext, MainActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE,
                ),
            ).setContentTitle("[Debug]APIアクセス終了")
            .setContentText(msg)
            .setChannelId(applicationContext.getString(R.string.schedule_notify_channel_id))
            .build()

        // 以下の if 式は自動生成されている
        // TODO: API Level 33 以降向けに通知許可を得るための処理を追加する
        if (ActivityCompat.checkSelfPermission(
                appContext,
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
        notificationManager.notify(2, notification)
    }
}
