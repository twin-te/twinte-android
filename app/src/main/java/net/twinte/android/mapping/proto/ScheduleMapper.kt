package net.twinte.android.mapping.proto

import net.twinte.android.model.Timetable.Course.Schedule as ModelSchedule
import net.twinte.api.timetable.v1.Type.Schedule as ProtoSchedule

internal fun ProtoSchedule.asModel(): ModelSchedule =
    ModelSchedule(
        module = TODO(),
        day = TODO(),
        period = period,
        room = locations,
    )
