package net.twinte.android.widget

import net.twinte.android.R
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
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
import java.text.SimpleDateFormat
import java.util.*


/**
 * ウィジットの実装
 */
class TimetableWidget : AppWidgetProvider() {

    /**
     * システムからウィジットの更新をリクエストされた場合に実行される
     */
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        GlobalScope.launch {
            // 複数ある可能性のウィジットを更新
            for (appWidgetId in appWidgetIds) {
                updateAppWidget(
                    context,
                    appWidgetManager,
                    appWidgetId
                )
            }
        }
    }

    /**
     * 置かれているウィジットの数が 0->1 になった場合に呼ばれる
     * 定期更新用のJobを設定
     */
    override fun onEnabled(context: Context) {
        val jobInfo =
            JobInfo.Builder(UpdateWidgetJobService.JOB_ID, ComponentName(context, UpdateWidgetJobService::class.java))
                .setBackoffCriteria(1 * 60 * 1000L, JobInfo.BACKOFF_POLICY_LINEAR)
                .setPersisted(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresCharging(false)
                .setPeriodic(15 * 60 * 1000)
                .build()
        val scheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.schedule(jobInfo)
        Log.d("WIDGET", "enabled")
    }

    /**
     * すべてのウィジットが削除された場合に呼ばれる
     * 定期更新用のJobを解除
     */
    override fun onDisabled(context: Context) {
        val scheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.cancel(UpdateWidgetJobService.JOB_ID)
        Log.d("WIDGET", "disabled")
    }

    /**
     * ウィジットのボタンからのインデントを受け取る
     */
    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        val appWidgetManager = AppWidgetManager.getInstance(context)
        Log.d("WIDGET", "receive ${intent.action}")
        GlobalScope.launch {
            updateAppWidget(
                context,
                appWidgetManager,
                intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID),
                intent
            )
        }

    }

    companion object {

        const val NEXT_DATE = "NEXT_DATE"
        const val PREV_DATE = "PREV_DATE"
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)
        var date = simpleDateFormat.format(Date())

        /**
         * 実際にウィジットを更新する
         */
        suspend fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int, intent: Intent? = null
        ) {
            date = when (intent?.action) {
                NEXT_DATE -> date.addDay(1)
                PREV_DATE -> date.addDay(-1)
                else -> simpleDateFormat.format(Date())
            }

            Log.d("WIDGET", date)

            // RemoteView作成
            val views = RemoteViews(
                context.packageName,
                R.layout.timetable_widget
            )
            // 初期化
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
                        setRemoteAdapter(R.id.period_list_view, Intent(context, WidgetListViewService::class.java).apply {
                            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                            data = Uri.parse(this.toUri(Intent.URI_INTENT_SCHEME))
                        })

                        setOnClickPendingIntent(
                            R.id.next_day_button,
                            PendingIntent.getBroadcast(
                                context,
                                appWidgetId,
                                Intent(context, TimetableWidget::class.java).apply {
                                    action = NEXT_DATE
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
                                    action = PREV_DATE
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
                views.run {
                    setViewVisibility(R.id.widget_error_wrapper, View.VISIBLE)
                    setTextViewText(R.id.widget_error_text_view, "エラーが発生しました：\n${e.message}")

                    setOnClickPendingIntent(
                        R.id.widget_error_reload_button, PendingIntent.getBroadcast(
                            context,
                            appWidgetId,
                            Intent(context, TimetableWidget::class.java).apply {
                                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                            }, 0
                        )
                    )
                }
                throw e
            } finally {
                views.setViewVisibility(R.id.widget_loading_wrapper, View.GONE)

                // 時間割ListViewアダプタのデータを更新
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.period_list_view)
                // 構築したViewで更新
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        }


    }
}

fun String.addDay(v: Int): String {
    val c = java.util.Calendar.getInstance()
    c.time = TimetableWidget.simpleDateFormat.parse(this) ?: throw Exception()
    c.add(java.util.Calendar.DATE, v)
    return TimetableWidget.simpleDateFormat.format(c.time)
}

fun String.label(): String {
    val c = java.util.Calendar.getInstance()
    c.time = TimetableWidget.simpleDateFormat.parse(this) ?: throw Exception()
    return SimpleDateFormat("MM/dd (E)", Locale.JAPAN).format(c.time)
}
