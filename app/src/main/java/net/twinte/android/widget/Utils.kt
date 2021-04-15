package net.twinte.android.widget

import android.content.Context
import android.widget.RemoteViews
import net.twinte.android.R
import net.twinte.android.model.Day
import net.twinte.android.model.EventType
import net.twinte.android.model.Timetable
import java.text.SimpleDateFormat
import java.util.*

/**
 * ウィジットで表示される授業のVM
 */
data class WidgetCourseViewModel(val name: String, val room: String, val time: String, val id: String?)

/**
 * Module名 + 日付
 */
fun Timetable.dateLabel(calendar: Calendar): String {
    val formatter = SimpleDateFormat("MM/dd (E)", Locale.JAPAN)
    return "${module.module.m} ${formatter.format(calendar.time)}"
}

/**
 * イベントラベル
 */
fun Timetable.eventLabel(): String {
    if (events.isEmpty()) return "通常日課"

    val changeTo = events.find { it.eventType == EventType.SubstituteDay }?.changeTo?.d
    if (changeTo != null) return "${changeTo}曜日程"

    return events.first().eventType.e
}

/**
 * ○コマの授業
 */
fun Timetable.courseCountLabel(): String {
    val module = module.module
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
    val module = module.module
    val parsedDate =
        SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN).let { f -> Calendar.getInstance().apply { time = f.parse(date) } }

    val targets = courses.filter { course ->
        val schedule = course.schedules ?: course.course!!.schedules
        schedule.any { it.module == module && it.period == period && it.day == Day.values()[parsedDate.get(Calendar.DAY_OF_WEEK) - 1] }
    }

    if (targets.isEmpty()) return null
    else if (targets.size > 1) return WidgetCourseViewModel(
        "${targets.size}件の授業重複あり",
        "-",
        WidgetUpdater.getPeriodStartTime(period).toString(),
        null
    )

    val target = targets.first()
    val targetName = target.name ?: target.course!!.name

    val schedule = target.schedules ?: target.course!!.schedules
    val targetSchedule = schedule.find { it.module == module && it.period == period }

    return WidgetCourseViewModel(
        targetName, targetSchedule!!.room,
        WidgetUpdater.getPeriodStartTime(period).toString(),
        target.id
    )
}

/**
 * 指定された時限以降で一番早い授業の表示用データを作成
 */
fun Timetable.nextCourseViewModel(period: Int): WidgetCourseViewModel? {
    var nextCourse: WidgetCourseViewModel? = null
    var p = period + 1
    while(p <= 6 && nextCourse == null) {
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
}
