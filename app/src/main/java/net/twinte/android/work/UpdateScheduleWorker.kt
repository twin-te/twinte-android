package net.twinte.android.work

import android.content.Context
import android.util.Log
import androidx.work.*
import net.twinte.android.repository.ScheduleRepository
import java.util.*
import java.util.concurrent.TimeUnit

class UpdateScheduleWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    companion object {
        const val TAG = "UPDATE_SCHEDULE"
        fun scheduleNextUpdate(context: Context) {
            val currentDate = Calendar.getInstance()

            // Set Execution around 09:00:00 AM
            val dueDate = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 9)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
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
                    OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .addTag(TAG).build()
            WorkManager.getInstance(context)
                .enqueueUniqueWork(TAG, ExistingWorkPolicy.REPLACE, updateScheduleWorkRequest)
            Log.d("UpdateScheduleWorker", "work enqueued")
        }
    }

    override suspend fun doWork() = try {
        ScheduleRepository(applicationContext).update()
        scheduleNextUpdate(applicationContext)
        Log.d("UpdateScheduleWorker", "work success")
        Result.success()
    } catch (e: Throwable) {
        Log.d("UpdateScheduleWorker", "work failure $e")
        Result.Retry()
    }
}
