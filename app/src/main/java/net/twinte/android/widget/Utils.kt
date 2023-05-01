package net.twinte.android.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.RemoteViews
import net.twinte.android.R
import net.twinte.android.model.Day
import net.twinte.android.model.EventType
import net.twinte.android.model.Timetable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Module名 + 日付
 */
fun Timetable.dateLabel(calendar: Calendar): String {
    val formatter = SimpleDateFormat("MM/dd (E)", Locale.JAPAN)
    val date = formatter.format(calendar.time)
    return if (module?.module?.m == null) {
        date
    } else {
        "${module.module.m} $date"
    }
}

/**
 * イベントラベル
 */
fun Timetable.eventLabel(): Pair<String, Boolean> {
    if (events.isEmpty()) return Pair("通常日課", false)

    val changeTo = events.find { it.eventType == EventType.SubstituteDay }?.changeTo?.d
    if (changeTo != null) return Pair("${changeTo}曜日課", true)

    return Pair(events.first().description, false)
}

/**
 * ○コマの授業
 */
fun Timetable.courseCountLabel(): String {
    val module = module?.module ?: return "0コマの授業"
    val parsedDate =
        SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN).let { f -> Calendar.getInstance().apply { time = f.parse(date) } }

    val count = (1..6).filter { period ->
        courses.any { course ->
            val schedule = course.schedules ?: course.course!!.schedules
            schedule.any { it.module == module && it.period == period && it.day == Day.values()[parsedDate.get(Calendar.DAY_OF_WEEK) - 1] }
        }
    }.size

    return "${count}コマの授業"
}

/**
 * 指定された時限の授業の表示用データを作成
 */
fun Timetable.courseViewModel(period: Int): WidgetCourseViewModel? {
    val module = module?.module ?: return null
    val parsedDate =
        SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN).let { f -> Calendar.getInstance().apply { time = f.parse(date) } }
    val day = events.find { it.changeTo != null }?.changeTo ?: Day.values()[parsedDate.get(Calendar.DAY_OF_WEEK) - 1]

    val targets = courses.filter { course ->
        val schedule = course.schedules ?: course.course!!.schedules
        schedule.any { it.module == module && it.period == period && it.day == day }
    }

    if (targets.isEmpty()) {
        return null
    } else if (targets.size > 1) {
        return WidgetCourseViewModel(
            "${targets.size}件の授業重複あり",
            "-",
            WidgetUpdater.getPeriodStartTime(period).toString(),
            null,
        )
    }

    val target = targets.first()
    val targetName = target.name ?: target.course!!.name

    val schedule = target.schedules ?: target.course!!.schedules
    val targetSchedule = schedule.find { it.module == module && it.period == period }

    return WidgetCourseViewModel(
        targetName,
        targetSchedule!!.room,
        WidgetUpdater.getPeriodStartTime(period).toString(),
        target.id,
    )
}

/**
 * 指定された時限以降で一番早い授業の表示用データを作成
 */
fun Timetable.nextCourseViewModel(period: Int): WidgetCourseViewModel? {
    var nextCourse: WidgetCourseViewModel? = null
    var p = period + 1
    while (p <= 6 && nextCourse == null) {
        nextCourse = courseViewModel(p)
        p++
    }
    return nextCourse
}

/**
 * 授業表示のコントロールを持つViewに内容を適用する
 */
fun RemoteViews.applyCourseItem(context: Context, model: WidgetCourseViewModel?) {
    setTextViewText(R.id.course_name_textView, model?.name ?: "授業がありません")
    setTextViewText(R.id.course_room_textView, model?.room ?: "-")
    setTextViewText(R.id.course_time_textView, model?.time ?: "-")

    val textColor = context.getColor(if (model != null) R.color.widget_text_main else R.color.widget_text_disabled)

    setTextColor(R.id.course_name_textView, textColor)
    setTextColor(R.id.course_room_textView, textColor)
    setTextColor(R.id.course_time_textView, textColor)

    val iconColor = context.getColor(if (model != null) R.color.colorPrimary else R.color.widget_text_disabled)

    setInt(R.id.course_room_imageView, "setColorFilter", iconColor)
    setInt(R.id.course_time_imageView, "setColorFilter", iconColor)
}

fun AppWidgetProvider.errorView(context: Context, widgetId: Int, title: String, detail: String? = null) =
    RemoteViews(
        context.packageName,
        R.layout.widget_v3_error,
    ).apply {
        setTextViewText(R.id.error_title_textView, title)
        if (detail != null) {
            setTextViewText(R.id.error_detail_textView, detail)
        } else {
            setViewVisibility(R.id.error_detail_textView, View.GONE)
        }

        setOnClickPendingIntent(
            R.id.error_reload_button,
            PendingIntent.getBroadcast(
                context,
                widgetId,
                Intent(context, this@errorView::class.java).apply {
                    action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                    putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, intArrayOf(widgetId))
                },
                PendingIntent.FLAG_IMMUTABLE,
            ),
        )
    }
