package net.twinte.android.mapping.proto

import android.os.Build
import androidx.annotation.RequiresApi
import net.twinte.android.model.Timetable
import net.twinte.api.schoolcalendar.v1.Type.Module
import net.twinte.api.unified.v1.Service.GetByDateResponse
import java.time.LocalDate
import net.twinte.android.model.Module as ModelModule
import net.twinte.api.schoolcalendar.v1.Type.Module as ProtoModule

@RequiresApi(Build.VERSION_CODES.O)
internal fun GetByDateResponse.toTimetable(reqDate: LocalDate): Timetable {
    val currentModule = module
        .takeIf { it != Module.MODULE_UNSPECIFIED }
        ?.let { Timetable.CurrentModule(reqDate.year, it.asModel()) }

    return Timetable(
        date = reqDate.toString(),
        module = currentModule,
        events = eventsList.map { it.asModel() }.toTypedArray(),
        courses = registeredCoursesList.map { it.asModel() }.toTypedArray(),
    )
}

internal fun ProtoModule.asModel(): ModelModule = when (this) {
    ProtoModule.MODULE_UNSPECIFIED -> ModelModule.Unknown
    ProtoModule.MODULE_SPRING_A -> ModelModule.SpringA
    ProtoModule.MODULE_SPRING_B -> ModelModule.SpringB
    ProtoModule.MODULE_SPRING_C -> ModelModule.SpringC
    ProtoModule.MODULE_SUMMER_VACATION -> ModelModule.SummerVacation
    ProtoModule.MODULE_FALL_A -> ModelModule.FallA
    ProtoModule.MODULE_FALL_B -> ModelModule.FallB
    ProtoModule.MODULE_WINTER_VACATION -> ModelModule.Unknown // TODO: Fix
    ProtoModule.MODULE_FALL_C -> ModelModule.FallC
    ProtoModule.MODULE_SPRING_VACATION -> ModelModule.SpringVacation
    ProtoModule.UNRECOGNIZED -> ModelModule.Unknown
}
