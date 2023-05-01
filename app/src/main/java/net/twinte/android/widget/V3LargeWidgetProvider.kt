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
import net.twinte.android.Network
import net.twinte.android.R
import net.twinte.android.TWINTE_DEBUG
import net.twinte.android.model.Timetable
import net.twinte.android.repository.ScheduleRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Largeウィジットの管理を担う
 */
class V3LargeWidgetProvider : AppWidgetProvider() {

    /**
     * 設置されたLargeウィジットの数が 0 -> 1 になると呼び出される
     */
    override fun onEnabled(context: Context) {
        WidgetUpdater.schedule(context, this::class.java)
    }

    /**
     * Largeウィジットが全て無くなると呼び出される
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
        appWidgetIds: IntArray,
    ) = runBlocking {
        Log.d("V3LargeWidgetProvider", "OnUpdate received")
        val (current, period) = WidgetUpdater.getShouldShowCurrentDate()

        try {
            val schedule = ScheduleRepository(context).getSchedule(current.time)

            appWidgetIds.forEach { appWidgetId ->
                val views = RemoteViews(
                    context.packageName,
                    R.layout.widget_v3_large,
                )

                views.setTextViewText(R.id.date_textView, schedule.dateLabel(current))
                schedule.eventLabel().let { (label, attention) ->
                    views.setTextViewText(R.id.event_textView, label)
                    views.setTextColor(
                        R.id.event_textView,
                        context.getColor(if (attention) R.color.widget_text_danger else R.color.widget_text_main),
                    )
                }
                views.setTextViewText(R.id.course_count_textView, schedule.courseCountLabel())

                if (TWINTE_DEBUG) {
                    views.setTextViewText(
                        R.id.debug_textView,
                        "last update: " + SimpleDateFormat(
                            "MM/dd HH:mm:ss",
                            Locale.JAPAN,
                        ).format(Calendar.getInstance().time),
                    )
                }

                views.setRemoteAdapter(
                    R.id.course_listView,
                    Intent(context, V3LargeWidgetRemoteViewService::class.java).apply {
                        putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                    },
                )

                views.setPendingIntentTemplate(
                    R.id.course_listView,
                    PendingIntent.getActivity(
                        context,
                        0,
                        Intent(context, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        },
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
                    ),
                )

                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.course_listView)
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        } catch (e: Network.NotLoggedInException) {
            appWidgetIds.forEach { appWidgetId ->
                appWidgetManager.updateAppWidget(appWidgetId, errorView(context, appWidgetId, "ログインしてください"))
            }
        } catch (e: Throwable) {
            appWidgetIds.forEach { appWidgetId ->
                appWidgetManager.updateAppWidget(
                    appWidgetId,
                    errorView(context, appWidgetId, "エラーが発生しました", e.stackTraceToString()),
                )
            }
        }
    }
}

/**
 * ウィジット右側のリストを生成するサービス
 */
class V3LargeWidgetRemoteViewService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?) = Factory(applicationContext, intent)

    class Factory(val context: Context, val intent: Intent?) : RemoteViewsFactory {
        var schedule: Timetable? = null

        override fun onCreate() {}

        override fun onDataSetChanged() = runBlocking {
            Log.d("LargeFactory", "onDataSetChanged")
            val (current, _) = WidgetUpdater.getShouldShowCurrentDate()
            schedule = ScheduleRepository(context).getSchedule(current.time)
        }

        override fun onDestroy() {}

        override fun getCount() = 6

        override fun getViewAt(position: Int): RemoteViews {
            val views = RemoteViews(
                context.packageName,
                R.layout.widget_v3_period_item,
            )

            views.setTextViewText(R.id.period_number_textView, "${position + 1}")
            val course = schedule?.courseViewModel(position + 1)
            views.applyCourseItem(context, course)

            views.setOnClickFillInIntent(
                R.id.period_item_wrapper,
                Intent().apply {
                    course?.id?.let {
                        putExtra("REGISTERED_COURSE_ID", it)
                    }
                },
            )

            return views
        }

        override fun getLoadingView() = null

        override fun getViewTypeCount() = 1

        override fun getItemId(position: Int) = position.toLong()
        override fun hasStableIds() = false
    }
}
