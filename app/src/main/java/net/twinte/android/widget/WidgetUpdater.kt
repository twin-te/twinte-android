package net.twinte.android.widget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*

/**
 * ウィジットの更新タイミングの制御を担う
 */
object WidgetUpdater {
    data class SimpleTime(val hour: Int, val minute: Int) {
        override fun toString() = "$hour:$minute"
    }

    data class PeriodTime(val start: SimpleTime, val end: SimpleTime)

    /**
     * 時限の開始時刻と終了時刻
     */
    private val periods = arrayOf(
        PeriodTime(SimpleTime(8, 40), SimpleTime(9, 55)),
        PeriodTime(SimpleTime(10, 10), SimpleTime(11, 25)),
        PeriodTime(SimpleTime(12, 15), SimpleTime(13, 30)),
        PeriodTime(SimpleTime(13, 45), SimpleTime(15, 0)),
        PeriodTime(SimpleTime(15, 15), SimpleTime(16, 30)),
        PeriodTime(SimpleTime(16, 45), SimpleTime(18, 0)),
    )

    /**
     * ウィジットの更新時に発行されるPendingIntentを生成する
     */
    private fun updateWidgetIntent(context: Context, clazz: Class<*>, time: SimpleTime) =
        Intent(context, clazz).let { intent ->
            intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            PendingIntent.getBroadcast(
                context,
                "${time.hour}${time.minute}".toInt(),
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
        }

    /**
     * 指定された時間に毎日ウィジットを更新するAlarmを設定する
     */
    private fun scheduleDailyAt(context: Context, clazz: Class<*>, time: SimpleTime, offsetMinute: Int = 0) {
        val alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, time.hour)
                set(Calendar.MINUTE, time.minute)
                add(Calendar.MINUTE, offsetMinute)
                if (before(Calendar.getInstance()))
                    add(Calendar.DATE, 1)
            }.timeInMillis,
            1000 * 60 * 60 * 24,
            updateWidgetIntent(context, clazz, time)
        )
        Log.d("WidgetUpdater", "scheduled ${clazz.simpleName} at $time")
    }

    /**
     * 指定されたAlarmを解除する
     */
    private fun cancelDailyAt(context: Context, clazz: Class<*>, time: SimpleTime) {
        val alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.cancel(
            updateWidgetIntent(context, clazz, time)
        )
        Log.d("WidgetUpdater", "canceled ${clazz.simpleName} at $time")
    }

    /**
     * 時限の開始時にウィジットを更新するAlarmをセット
     */
    private fun scheduleAtPeriodStart(context: Context, clazz: Class<*>, offsetMinute: Int) {
        periods.map { it.start }.forEach { date ->
            scheduleDailyAt(context, clazz, date, offsetMinute)
        }
        Log.d("WidgetUpdater", "scheduleAtPeriodStart ${clazz.simpleName}")
    }

    /**
     * 時限の開始時にウィジットを更新するAlarmをキャンセル
     */
    private fun cancelScheduleAtPeriodStart(context: Context, clazz: Class<*>) {
        periods.map { it.start }.forEach { time ->
            cancelDailyAt(context, clazz, time)
        }
        Log.d("WidgetUpdater", "cancelScheduleAtPeriodStart ${clazz.simpleName}")
    }

    /**
     * 存在する全てのウィジットを強制アップデート
     */
    fun updateAllWidget(context: Context) {
        val appWidgetManager = context.getSystemService(AppWidgetManager::class.java)

        arrayOf(
            V3SmallWidgetProvider::class.java,
            V3MediumWidgetProvider::class.java,
            V3LargeWidgetProvider::class.java
        ).forEach { clazz ->
            context.sendBroadcast(Intent(context, clazz).apply {
                action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                putExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetManager.getAppWidgetIds(
                        ComponentName(
                            context,
                            clazz
                        )
                    )
                )
            })
        }
    }

    /**
     * 「現在の授業」として表示すべき授業の日付と時限を返す
     */
    fun getShouldShowCurrentDate(current: Calendar = Calendar.getInstance()): Pair<Calendar, Int> {
        return if (current.get(Calendar.HOUR_OF_DAY) > 18)
            Pair(current.apply { add(Calendar.DATE, 1) }, 0)
        else {
            val currentPeriod = periods.findLast {
                current.after(Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, it.start.hour)
                    set(Calendar.MINUTE, it.start.minute)
                })
            } ?: return Pair(current, 0)
            Pair(current, periods.indexOf(currentPeriod) + 1)
        }
    }

    /**
     * 指定した時限の開始時間を返す
     */
    fun getPeriodStartTime(period: Int): SimpleTime {
        return periods[period - 1].start
    }

    /**
     * 指定されたウィジットの更新をスケジュールする
     */
    fun schedule(context: Context, clazz: Class<*>) {
        when (clazz) {
            // Smallウィジットは各授業の開始時刻と18:30に表示を更新する
            V3SmallWidgetProvider::class.java -> {
                scheduleAtPeriodStart(context, clazz, 0)
                scheduleDailyAt(context, clazz, SimpleTime(18, 30))
            }
            // Mediumウィジットは各授業の開始時刻と18:30に表示を更新する
            V3MediumWidgetProvider::class.java -> {
                scheduleAtPeriodStart(context, clazz, 0)
                scheduleDailyAt(context, clazz, SimpleTime(18, 30))
            }
            // Largeウィジットはその日の授業が終了した後18:30の一回のみ表示を更新する
            V3LargeWidgetProvider::class.java -> scheduleDailyAt(context, clazz, SimpleTime(18, 30))
        }
    }

    /**
     * 指定されたウィジットの更新をキャンセルする
     */
    fun cancel(context: Context, clazz: Class<*>) {
        when (clazz) {
            V3SmallWidgetProvider::class.java -> cancelScheduleAtPeriodStart(context, clazz)
            V3MediumWidgetProvider::class.java -> cancelScheduleAtPeriodStart(context, clazz)
            V3LargeWidgetProvider::class.java -> cancelDailyAt(context, clazz, SimpleTime(18, 30))
        }
    }
}

/**
 * スマホが再起動した時にウィジットの更新をスケジュールし直す
 */
class WidgetUpdaterSchedulerOnBootComplete : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val appWidgetManager = context.getSystemService(AppWidgetManager::class.java)
        if (appWidgetManager.getAppWidgetIds(ComponentName(context, V3SmallWidgetProvider::class.java)).isNotEmpty())
            WidgetUpdater.schedule(context, V3SmallWidgetProvider::class.java)
        if (appWidgetManager.getAppWidgetIds(ComponentName(context, V3MediumWidgetProvider::class.java)).isNotEmpty())
            WidgetUpdater.schedule(context, V3MediumWidgetProvider::class.java)
        if (appWidgetManager.getAppWidgetIds(ComponentName(context, V3LargeWidgetProvider::class.java)).isNotEmpty())
            WidgetUpdater.schedule(context, V3LargeWidgetProvider::class.java)
    }
}
