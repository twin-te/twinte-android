package net.twinte.android.mapping.proto

import net.twinte.android.model.Timetable.Event as ModuleEvent
import net.twinte.api.schoolcalendar.v1.Type.Event as ProtoEvent

internal fun ProtoEvent.asModel(): ModuleEvent =
    ModuleEvent(
        date = date.value,
        eventType = type.asModel(),
        description = description,
        changeTo = TODO(),
    )