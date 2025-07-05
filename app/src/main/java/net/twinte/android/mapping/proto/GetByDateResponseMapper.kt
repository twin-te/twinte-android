package net.twinte.android.mapping.proto

import android.os.Build
import androidx.annotation.RequiresApi
import net.twinte.android.model.Timetable
import net.twinte.api.schoolcalendar.v1.Type.Module
import net.twinte.api.unified.v1.Service.GetByDateResponse
import java.time.LocalDate

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
