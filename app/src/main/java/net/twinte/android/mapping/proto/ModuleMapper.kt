package net.twinte.android.mapping.proto

import net.twinte.android.model.Module as ModelModule
import net.twinte.api.timetable.v1.Type.Module as ProtoModule

internal fun ProtoModule.asModel(): ModelModule = when (this) {
    ProtoModule.MODULE_UNSPECIFIED -> ModelModule.Unknown
    ProtoModule.MODULE_SPRING_A -> ModelModule.SpringA
    ProtoModule.MODULE_SPRING_B -> ModelModule.SpringB
    ProtoModule.MODULE_SPRING_C -> ModelModule.SpringC
    ProtoModule.MODULE_SUMMER_VACATION -> ModelModule.SummerVacation
    ProtoModule.MODULE_FALL_A -> ModelModule.FallA
    ProtoModule.MODULE_FALL_B -> ModelModule.FallB
    ProtoModule.MODULE_FALL_C -> ModelModule.SpringC
    ProtoModule.MODULE_SPRING_VACATION -> ModelModule.SpringVacation
    ProtoModule.UNRECOGNIZED -> ModelModule.Unknown
}
