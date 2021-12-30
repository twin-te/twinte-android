package net.twinte.android.model

data class Timetable(
    val date: String,
    val module: CurrentModule?,
    val events: Array<Event>,
    val courses: Array<Course>
) {
    data class CurrentModule(
        val year: Int,
        val module: Module
    )

    data class Event(
        val date: String,
        val eventType: EventType,
        val description: String,
        val changeTo: Day?
    )

    data class Course(
        val id: String,
        val schedules: Array<Schedule>?,
        val methods: Array<Format>?,
        val name: String?,
        val instructor: String?,
        val year: Int?,
        val course: BaseCourse?
    ) {
        data class Schedule(
            val module: Module,
            val day: Day,
            val period: Int,
            val room: String
        )

        data class BaseCourse(
            val schedules: Array<Schedule>,
            val methods: Array<Format>,
            val name: String,
            val instructor: String,
            val year: Int
        )
    }
}
