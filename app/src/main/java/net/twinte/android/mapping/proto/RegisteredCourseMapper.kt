package net.twinte.android.mapping.proto

import net.twinte.android.model.Timetable.Course as ModelCourse
import net.twinte.api.timetable.v1.Type.RegisteredCourse as ProtoCourse

internal fun ProtoCourse.asModel(): ModelCourse =
    ModelCourse(
        id = id.value,
        name = name,
        instructor = instructors,
        year = year.value,
        methods = TODO(),
        schedules = schedulesList.map { it.asModel() }.toTypedArray(),
        course = takeIf { it.methodsCount > 0 || it.schedulesCount > 0 }?.let { bc ->
            ModelCourse.BaseCourse(
                name = bc.name,
                instructor = bc.instructors,
                year = bc.year.value,
                methods = TODO(),
                schedules = bc.schedulesList.map { it.asModel() }.toTypedArray(),
            )
        },
    )
