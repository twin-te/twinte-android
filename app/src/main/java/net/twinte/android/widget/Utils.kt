package net.twinte.android.widget

import android.content.Context
import android.widget.RemoteViews
import net.twinte.android.R
import net.twinte.android.model.Day
import net.twinte.android.model.EventType
import net.twinte.android.model.Timetable
import java.text.SimpleDateFormat
import java.util.*


fun Timetable.dateLabel(calendar: Calendar): String {
    val formatter = SimpleDateFormat("MM/dd (E)", Locale.JAPAN)
    return "${module.module.m} ${formatter.format(calendar.time)}"
}

fun Timetable.eventLabel(): String {
    if (events.isEmpty()) return "通常日課"

    val changeTo = events.find { it.eventType == EventType.SubstituteDay }?.changeTo?.d
    if (changeTo != null) return "${changeTo}曜日程"

    return events.first().eventType.e
}

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

fun Timetable.courseViewModel(period: Int): WidgetUpdater.WidgetCourseViewModel? {
    val module = module.module
    val parsedDate =
        SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN).let { f -> Calendar.getInstance().apply { time = f.parse(date) } }

    val targets = courses.filter { course ->
        val schedule = course.schedules ?: course.course!!.schedules
        schedule.any { it.module == module && it.period == period && it.day == Day.values()[parsedDate.get(Calendar.DAY_OF_WEEK) - 1] }
    }

    if (targets.isEmpty()) return null
    else if (targets.size > 1) return WidgetUpdater.WidgetCourseViewModel(
        "${targets.size}件の授業重複あり",
        "-",
        WidgetUpdater.getPeriodStartTime(period).toString()
    )

    val target = targets.first()
    val targetName = target.name ?: target.course!!.name

    val schedule = target.schedules ?: target.course!!.schedules
    val targetSchedule = schedule.find { it.module == module && it.period == period }

    return WidgetUpdater.WidgetCourseViewModel(
        targetName, targetSchedule!!.room,
        WidgetUpdater.getPeriodStartTime(period).toString()
    )
}

fun RemoteViews.applyCourseItem(context: Context, model: WidgetUpdater.WidgetCourseViewModel?) {
    setTextViewText(R.id.course_name_textView, model?.name ?: "授業がありません")
    setTextViewText(R.id.course_room_textView, model?.room ?: "-")
    setTextViewText(R.id.course_time_textView, model?.time ?: "-")

    val textColor = context.getColor(if (model != null) R.color.widget_text_main else R.color.widget_text_disabled)

    setTextColor(R.id.course_name_textView, textColor)
    setTextColor(R.id.course_room_textView, textColor)
    setTextColor(R.id.course_time_textView, textColor)
}
