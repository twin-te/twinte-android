package net.twinte.android.mapping.proto

import net.twinte.android.model.Day as ModelDay
import net.twinte.android.model.Timetable.Course.Schedule as ModelSchedule
import net.twinte.api.timetable.v1.Type.Day as ProtoDay
import net.twinte.api.timetable.v1.Type.Schedule as ProtoSchedule

internal fun ProtoSchedule.asModel(): ModelSchedule =
    ModelSchedule(
        module = module.asModel(),
        day = day.asModel() ?: ModelDay.Sun, // TODO: Fix
        period = period,
        room = locations,
    )

internal fun ProtoDay.asModel(): ModelDay? = when (this) {
    ProtoDay.DAY_SUN -> ModelDay.Sun
    ProtoDay.DAY_MON -> ModelDay.Mon
    ProtoDay.DAY_TUE -> ModelDay.Tue
    ProtoDay.DAY_WED -> ModelDay.Wed
    ProtoDay.DAY_THU -> ModelDay.Thu
    ProtoDay.DAY_FRI -> ModelDay.Fri
    ProtoDay.DAY_SAT -> ModelDay.Sat
    else -> null
}
