package net.twinte.android.mapping.proto

import net.twinte.api.schoolcalendar.v1.Type.EventType as ProtoEventType
import net.twinte.android.model.EventType as ModelEventType

internal fun ProtoEventType.asModel():
        ModelEventType = when(this){
    ProtoEventType.EVENT_TYPE_UNSPECIFIED -> ModelEventType.Other
    ProtoEventType.EVENT_TYPE_HOLIDAY -> ModelEventType.Holiday
    ProtoEventType.EVENT_TYPE_PUBLIC_HOLIDAY -> ModelEventType.PublicHoliday
    ProtoEventType.EVENT_TYPE_EXAM -> ModelEventType.Exam
    ProtoEventType.EVENT_TYPE_SUBSTITUTE_DAY -> ModelEventType.SubstituteDay
    ProtoEventType.EVENT_TYPE_OTHER -> ModelEventType.Other
    ProtoEventType.UNRECOGNIZED -> ModelEventType.Other
}