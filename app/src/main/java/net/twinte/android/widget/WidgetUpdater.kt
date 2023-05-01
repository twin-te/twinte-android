package net.twinte.android.widget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar

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
            val appWidgetManager = context.getSystemService(AppWidgetManager::class.java)
            intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            intent.putExtra(
                AppWidgetManager.EXTRA_APPWIDGET_IDS,
                appWidgetManager.getAppWidgetIds(
                    ComponentName(
                        context,
                        clazz
                    )
                )
            )
            PendingIntent.getBroadcast(
                context,
                "${time.hour}${time.minute}".toInt(),
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

    /**
     * 指定された時間に毎日ウィジットを更新するAlarmを設定する
     */
    private fun scheduleDailyAt(context: Context, clazz: Class<*>, time: SimpleTime, offsetMinute: Int = 0) {
        val alarmManager = context.getSystemService(AlarmManager::class.java)
        val at = time.toCalendar().apply {
            add(Calendar.MINUTE, offsetMinute)
            if (before(Calendar.getInstance()))
                add(Calendar.DATE, 1)
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            at.timeInMillis,
            1000 * 60 * 60 * 24,
            updateWidgetIntent(context, clazz, time)
        )
        Log.d(
            "WidgetUpdater",
            "scheduled ${clazz.simpleName} at $time ${SimpleDateFormat.getDateTimeInstance().format(at.time)}"
        )
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
                    AppWidgetManager.EXTRA_APPWIDGET_IDS,
                    appWidgetManager.getAppWidgetIds(
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
    fun getShouldShowCurrentDate(
        current: Calendar = Calendar.getInstance()
    ): Pair<Calendar, Int> {
        return when {
            // 19時以降は明日の0限を表示
            current.get(Calendar.HOUR_OF_DAY) > 18 -> Pair(current.apply { add(Calendar.DATE, 1) }, 0)
            // 1限開始前は今日の0限を表示
            current.before(periods.first().start.toCalendar()) -> Pair(current, 0)
            // 6現終了後は7限(空)を表示
            current.after(periods.last().end.toCalendar()) -> Pair(current, 7)
            // 1~6限中
            else -> {
                val currentPeriod = periods.findLast {
                    current.after(it.start.toCalendar())
                } ?: return Pair(current, 0)
                Pair(current, periods.indexOf(currentPeriod) + 1)
            }
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
        cancel(context, clazz)
        when (clazz) {
            // Smallウィジットは各授業の開始時刻から30分遅れて更新
            V3SmallWidgetProvider::class.java -> {
                scheduleAtPeriodStart(context, clazz, 30)
                scheduleDailyAt(context, clazz, SimpleTime(18, 0)) // 空になる
                scheduleDailyAt(context, clazz, SimpleTime(19, 0)) // 明日の授業が表示
            }
            // Mediumウィジットは各授業の開始時刻に更新する
            V3MediumWidgetProvider::class.java -> {
                scheduleAtPeriodStart(context, clazz, 0)
                scheduleDailyAt(context, clazz, SimpleTime(18, 0)) // 空になる
                scheduleDailyAt(context, clazz, SimpleTime(19, 0)) // 明日の授業が表示
            }
            // Largeウィジットはその日の授業が終了した後19:00の一回のみ表示を更新する
            V3LargeWidgetProvider::class.java -> scheduleDailyAt(context, clazz, SimpleTime(19, 0))
        }
    }

    fun scheduleAllIfExists(context: Context) {
        val appWidgetManager = context.getSystemService(AppWidgetManager::class.java)
        if (appWidgetManager.getAppWidgetIds(ComponentName(context, V3SmallWidgetProvider::class.java))
                .isNotEmpty()
        )
            schedule(context, V3SmallWidgetProvider::class.java)
        if (appWidgetManager.getAppWidgetIds(ComponentName(context, V3MediumWidgetProvider::class.java))
                .isNotEmpty()
        )
            schedule(context, V3MediumWidgetProvider::class.java)
        if (appWidgetManager.getAppWidgetIds(ComponentName(context, V3LargeWidgetProvider::class.java))
                .isNotEmpty()
        )
            schedule(context, V3LargeWidgetProvider::class.java)
    }

    /**
     * 指定されたウィジットの更新をキャンセルする
     */
    fun cancel(context: Context, clazz: Class<*>) {
        when (clazz) {
            V3SmallWidgetProvider::class.java -> {
                cancelScheduleAtPeriodStart(context, clazz)
                cancelDailyAt(context, clazz, SimpleTime(18, 0))
                cancelDailyAt(context, clazz, SimpleTime(19, 0))
            }
            V3MediumWidgetProvider::class.java -> {
                cancelScheduleAtPeriodStart(context, clazz)
                cancelDailyAt(context, clazz, SimpleTime(18, 0))
                cancelDailyAt(context, clazz, SimpleTime(19, 0))
            }
            V3LargeWidgetProvider::class.java -> cancelDailyAt(context, clazz, SimpleTime(19, 0))
        }
    }

    private fun SimpleTime.toCalendar(): Calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }


    /**
     * スマホが再起動した時にウィジットの更新をスケジュールし直す
     */
    class OnBootCompleteOrPackageReplaced : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            Log.d("WidgetUpdater", "onReceived ${intent?.action}")
            scheduleAllIfExists(context)
        }
    }
}

