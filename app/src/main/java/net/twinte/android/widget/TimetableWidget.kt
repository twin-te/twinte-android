package net.twinte.android.widget

import net.twinte.android.R
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.twinte.android.MainActivity
import net.twinte.android.Util
import net.twinte.android.WebViewCookieJar
import net.twinte.android.types.Calendar
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


/**
 * ウィジットの実装
 */
class TimetableWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // 複数ある可能性のウィジットを更新
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context,
                appWidgetManager,
                appWidgetId
            )
        }
    }

    override fun onEnabled(context: Context) {}

    override fun onDisabled(context: Context) {}

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        val appWidgetManager = AppWidgetManager.getInstance(context)
        Log.d("WIDGET", "receive ${intent.action} ${intent.getStringExtra("date")}")
        updateAppWidget(
            context,
            appWidgetManager,
            intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID),
            intent
        )
    }

    companion object {

        const val NEXT_DATE = "NEXT_DATE"
        const val PREV_DATE = "PREV_DATE"
        const val rnd = 114514
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        var date = dateFormat.format(Date())

        fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int, intent: Intent? = null
        ) {
            GlobalScope.launch {
                if (intent?.action == NEXT_DATE)
                    date = date.addDay(1)
                else if (intent?.action == PREV_DATE)
                    date = date.addDay(-1)

                Log.d("WIDGET", date)

                // RemoteView作成
                val views = RemoteViews(
                    context.packageName,
                    R.layout.timetable_widget
                )

                views.setViewVisibility(R.id.widget_loading_wrapper, View.VISIBLE)
                views.setViewVisibility(R.id.widget_error_wrapper, View.GONE)
                appWidgetManager.updateAppWidget(appWidgetId, views)

                try {
                    val client = OkHttpClient.Builder().cookieJar(WebViewCookieJar()).build()
                    val req = Request.Builder().url("https://dev.api.twinte.net/v1/school-calender/$date").build()
                    val res = withContext(Dispatchers.IO) {
                        client.newCall(req).execute().body?.string()
                    }


                    if (!Util.isLoggedIn()) {
                        views.setTextViewText(R.id.date_text_view, "ログインしてください")
                    } else {
                        val gson = Gson()
                        val calendar = gson.fromJson<Calendar>(res, Calendar::class.java)

                        val eventText = when {
                            calendar.substituteDay != null -> "今日は${calendar.substituteDay.change_to.d}曜日程です"
                            calendar.event != null -> "${calendar.event.event_type.e} ${calendar.event.description}"
                            else -> if (calendar.module != null) calendar.module.m + "モジュール" else ""
                        }

                        views.run {
                            setTextViewText(R.id.date_text_view, date.label())
                            setTextViewText(R.id.event_text_view, eventText)
                            setViewVisibility(
                                R.id.event_text_view,
                                if (eventText.isBlank()) View.GONE else View.VISIBLE
                            )
                            setRemoteAdapter(R.id.period_list_view, Intent(context, WidgetService::class.java).apply {
                                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                                data = Uri.parse(this.toUri(Intent.URI_INTENT_SCHEME))
                            })

                            setOnClickPendingIntent(
                                R.id.next_day_button,
                                PendingIntent.getBroadcast(
                                    context,
                                    appWidgetId,
                                    Intent(context, TimetableWidget::class.java).apply {
                                        action = TimetableWidget.NEXT_DATE
                                        putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                                    },
                                    0
                                )
                            )

                            setOnClickPendingIntent(
                                R.id.prev_day_button,
                                PendingIntent.getBroadcast(
                                    context,
                                    appWidgetId,
                                    Intent(context, TimetableWidget::class.java).apply {
                                        action = TimetableWidget.PREV_DATE
                                        putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                                    }, 0
                                )
                            )

                            setPendingIntentTemplate(
                                R.id.period_list_view,
                                PendingIntent.getActivity(
                                    context,
                                    1,
                                    Intent(context, MainActivity::class.java).apply {
                                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                                    },
                                    0
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    views.setViewVisibility(R.id.widget_error_wrapper, View.VISIBLE)
                    views.setTextViewText(R.id.widget_error_text_view, e.message)
                }


                views.setViewVisibility(R.id.widget_loading_wrapper, View.GONE)

                // 時間割ListViewアダプタのデータを更新
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.period_list_view)
                // 構築したViewで更新
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun String.addDay(v: Int): String {
    val c = java.util.Calendar.getInstance()
    c.time = SimpleDateFormat("yyyy-MM-dd").parse(this) ?: throw Exception()
    c.add(java.util.Calendar.DATE, v)
    return SimpleDateFormat("yyyy-MM-dd").format(c.time)
}

fun String.label(): String {
    val c = java.util.Calendar.getInstance()
    c.time = SimpleDateFormat("yyyy-MM-dd").parse(this) ?: throw Exception()
    return SimpleDateFormat("MM/dd (E)", Locale.JAPAN).format(c.time)
}
