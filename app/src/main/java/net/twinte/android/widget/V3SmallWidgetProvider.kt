package net.twinte.android.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import kotlinx.coroutines.runBlocking
import net.twinte.android.MainActivity
import net.twinte.android.Network
import net.twinte.android.R
import net.twinte.android.TWINTE_DEBUG
import net.twinte.android.repository.ScheduleRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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

        try {
            val schedule = ScheduleRepository(context).getSchedule(current.time)

            appWidgetIds.forEach { appWidgetId ->
                val views = RemoteViews(
                    context.packageName,
                    R.layout.widget_v3_small
                )
                views.setTextViewText(R.id.date_textView, schedule.dateLabel(current))
                schedule.eventLabel().let { (label, attention) ->
                    views.setTextViewText(R.id.event_textView, label)
                    views.setTextColor(
                        R.id.event_textView,
                        context.getColor(if (attention) R.color.widget_text_danger else R.color.widget_text_main)
                    )
                }
                val nextCourse = schedule.nextCourseViewModel(period)
                views.applyCourseItem(context, nextCourse)

                // タップした授業の詳細画面を表示するIntentを作成
                views.setOnClickPendingIntent(
                    R.id.next_course_wrapper,
                    PendingIntent.getActivity(
                        context,
                        0,
                        Intent(context, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                            nextCourse?.id?.let {
                                putExtra("REGISTERED_COURSE_ID", it)
                            }
                        },
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )
                )

                if (TWINTE_DEBUG) {
                    views.setTextViewText(
                        R.id.debug_textView,
                        "last update: " + SimpleDateFormat(
                            "MM/dd HH:mm:ss",
                            Locale.JAPAN
                        ).format(Calendar.getInstance().time)
                    )
                }

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
                    errorView(context, appWidgetId, "エラーが発生しました", e.stackTraceToString())
                )
            }
        }
    }
}
