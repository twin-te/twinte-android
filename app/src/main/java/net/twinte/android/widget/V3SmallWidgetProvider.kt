package net.twinte.android.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import kotlinx.coroutines.runBlocking
import net.twinte.android.R
import net.twinte.android.repository.ScheduleRepository

/**
 * Smallウィジットの管理を担う
 */
class V3SmallWidgetProvider : AppWidgetProvider() {

    /**
     * 設置されたSmallウィジットの数が 0 -> 1 になると呼び出される
     */
    override fun onEnabled(context: Context) {
        WidgetUpdater.schedule(context, this::class.java)
    }

    /**
     * Smallウィジットが全て無くなると呼び出される
     */
    override fun onDisabled(context: Context) {
        WidgetUpdater.cancel(context, this::class.java)
    }

    /**
     * ウィジットの更新依頼が来たら呼び出される
     */
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) = runBlocking {
        Log.d("V3SmallWidgetProvider", "OnUpdate received")
        val (current, period) = WidgetUpdater.getShouldShowCurrentDate()
        val schedule = ScheduleRepository(context).getSchedule(current.time)

        appWidgetIds.forEach { appWidgetId ->
            val views: RemoteViews = RemoteViews(
                context.packageName,
                R.layout.widget_v3_small
            )
            views.setTextViewText(R.id.date_textView, schedule.dateLabel(current))
            views.setTextViewText(R.id.event_textView, schedule.eventLabel())
            val nextCourse = schedule.courseViewModel(period + 1)
            views.applyCourseItem(context, nextCourse)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
