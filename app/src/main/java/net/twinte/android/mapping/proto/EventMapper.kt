package net.twinte.android.mapping.proto

import net.twinte.android.model.Day
import net.twinte.api.shared.Type.Weekday
import net.twinte.android.model.Timetable.Event as ModuleEvent
import net.twinte.api.schoolcalendar.v1.Type.Event as ProtoEvent

internal fun ProtoEvent.asModel(): ModuleEvent =
    ModuleEvent(
        date = date.value,
        eventType = type.asModel(),
        description = description,
        changeTo = changeTo.asModel(),
    )

internal fun Weekday.asModel(): Day? = when(this) {
    Weekday.WEEKDAY_SUNDAY -> Day.Sun
    Weekday.WEEKDAY_MONDAY -> Day.Mon
    Weekday.WEEKDAY_TUESDAY -> Day.Tue
    Weekday.WEEKDAY_WEDNESDAY -> Day.Wed
    Weekday.WEEKDAY_THURSDAY -> Day.Thu
    Weekday.WEEKDAY_FRIDAY -> Day.Fri
    Weekday.WEEKDAY_SATURDAY -> Day.Sat
    else -> null
}