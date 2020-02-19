package net.twinte.android.widget

import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch

/**
 * ウィジット定期更新用Job
 */
class UpdateWidgetJobService : JobService() {

    companion object {
        const val JOB_ID = 1
    }

    var job: Job? = null

    override fun onStopJob(params: JobParameters?): Boolean {
        job?.cancel()
        Log.d("UpdateWidgetJobService", "onStopJob")
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("UpdateWidgetJobService", "onStartJob")
        val appWidgetManager = AppWidgetManager.getInstance(applicationContext)
        val widgetIds = appWidgetManager.getAppWidgetIds(ComponentName(applicationContext, TimetableWidget::class.java))
        job = GlobalScope.launch {
            widgetIds.forEach {
                TimetableWidget.updateAppWidget(
                    applicationContext,
                    appWidgetManager,
                    it
                )
            }
            jobFinished(params, false)
            Log.d("UpdateWidgetJobService", "jobFinished")
            val scheduler = applicationContext.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.allPendingJobs.forEach { Log.d("UpdateWidgetJobService", it.toString()) }
        }
        return true
    }
}
