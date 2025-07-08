package net.twinte.android.mapping.proto

import net.twinte.android.model.Format
import net.twinte.api.timetable.v1.Type.CourseMethod
import net.twinte.android.model.Timetable.Course as ModelCourse
import net.twinte.api.timetable.v1.Type.RegisteredCourse as ProtoCourse

internal fun ProtoCourse.asModel(): ModelCourse =
    ModelCourse(
        id = id.value,
        name = name,
        instructor = instructors,
        year = year.value,
        methods = methodsList.map { it.asModel() }.toTypedArray(),
        schedules = schedulesList.map { it.asModel() }.toTypedArray(),
        course = takeIf { it.methodsCount > 0 || it.schedulesCount > 0 }?.let { bc ->
            ModelCourse.BaseCourse(
                name = bc.name,
                instructor = bc.instructors,
                year = bc.year.value,
                methods = bc.methodsList.map { it.asModel() }.toTypedArray(),
                schedules = bc.schedulesList.map { it.asModel() }.toTypedArray(),
            )
        },
    )

internal fun CourseMethod.asModel(): Format = when (this) {
    CourseMethod.COURSE_METHOD_UNSPECIFIED -> Format.Others
    CourseMethod.COURSE_METHOD_ONLINE_ASYNCHRONOUS -> Format.OnlineAsynchronous
    CourseMethod.COURSE_METHOD_ONLINE_SYNCHRONOUS -> Format.OnlineSynchronous
    CourseMethod.COURSE_METHOD_FACE_TO_FACE -> Format.FaceToFace
    CourseMethod.COURSE_METHOD_OTHERS -> Format.Others
    CourseMethod.UNRECOGNIZED -> Format.Others
}
