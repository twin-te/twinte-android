package net.twinte.android.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import kotlinx.coroutines.runBlocking
import net.twinte.android.MainActivity
import net.twinte.android.R
import net.twinte.android.model.Timetable
import net.twinte.android.repository.ScheduleRepository

/**
 * Mediumウィジットの管理を担う
 */
class V3MediumWidgetProvider : AppWidgetProvider() {

    /**
     * 設置されたMediumウィジットの数が 0 -> 1 になると呼び出される
     */
    override fun onEnabled(context: Context) {
        WidgetUpdater.schedule(context, this::class.java)
    }

    /**
     * Mediumウィジットが全て無くなると呼び出される
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
        Log.d("V3MediumWidgetProvider", "OnUpdate received")
        val (current, period) = WidgetUpdater.getShouldShowCurrentDate()
        val schedule = ScheduleRepository(context).getSchedule(current.time)

        appWidgetIds.forEach { appWidgetId ->
            val views: RemoteViews = RemoteViews(
                context.packageName,
                R.layout.widget_v3_medium
            )

            views.setTextViewText(R.id.date_textView, schedule.dateLabel(current))
            views.setTextViewText(R.id.event_textView, schedule.eventLabel())
            views.setTextViewText(R.id.course_count_textView, schedule.courseCountLabel())

            views.setRemoteAdapter(
                R.id.course_listView,
                Intent(context, V3MediumWidgetRemoteViewService::class.java).apply {
                    putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                })

            views.setPendingIntentTemplate(
                R.id.course_listView,
                PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }, PendingIntent.FLAG_UPDATE_CURRENT)
            )

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.course_listView)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

/**
 * ウィジット右側のリストを生成するサービス
 */
class V3MediumWidgetRemoteViewService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?) = Factory(applicationContext, intent)

    class Factory(val context: Context, val intent: Intent?) : RemoteViewsService.RemoteViewsFactory {
        var schedule: Timetable? = null

        override fun onCreate() {}

        override fun onDataSetChanged() = runBlocking {
            val (current, _) = WidgetUpdater.getShouldShowCurrentDate()
            schedule = ScheduleRepository(context).getSchedule(current.time)
        }

        override fun onDestroy() {}

        override fun getCount() = 2

        override fun getViewAt(position: Int): RemoteViews {
            val (_, period) = WidgetUpdater.getShouldShowCurrentDate()
            val views: RemoteViews = RemoteViews(
                context.packageName,
                R.layout.widget_v3_course_item_with_header
            )

            val course = if (position == 0) schedule?.courseViewModel(period) else schedule?.nextCourseViewModel(period)
            val headerLabel = if (position == 0) "現在の授業" else "次の授業"
            views.setTextViewText(R.id.course_header_textView, headerLabel)
            views.applyCourseItem(context, course)

            views.setOnClickFillInIntent(R.id.course_item_with_header_wrapper, Intent().apply {
                course?.id?.let {
                    putExtra("REGISTERED_COURSE_ID", it)
                }
            })

            return views
        }

        override fun getLoadingView() = null

        override fun getViewTypeCount() = 1

        override fun getItemId(position: Int) = position.toLong()
        override fun hasStableIds() = false

    }
}
