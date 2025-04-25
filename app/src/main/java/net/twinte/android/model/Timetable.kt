package net.twinte.android.model

data class Timetable(
    val date: String,
    val module: CurrentModule?,
    val events: Array<Event>,
    val courses: Array<Course>,
) {
    data class CurrentModule(
        val year: Int,
        val module: Module,
    )

    data class Event(
        val date: String,
        val eventType: EventType,
        val description: String,
        val changeTo: Day?,
    )

    data class Course(
        val id: String,
        val schedules: Array<Schedule>?,
        val methods: Array<Format>?,
        val name: String?,
        val instructor: String?,
        val year: Int?,
        val course: BaseCourse?,
    ) {
        data class Schedule(
            val module: Module,
            val day: Day,
            val period: Int,
            val room: String,
        )

        data class BaseCourse(
            val schedules: Array<Schedule>,
            val methods: Array<Format>,
            val name: String,
            val instructor: String,
            val year: Int,
        ) {
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as BaseCourse

                if (year != other.year) return false
                if (!schedules.contentEquals(other.schedules)) return false
                if (!methods.contentEquals(other.methods)) return false
                if (name != other.name) return false
                if (instructor != other.instructor) return false

                return true
            }

            override fun hashCode(): Int {
                var result = year
                result = 31 * result + schedules.contentHashCode()
                result = 31 * result + methods.contentHashCode()
                result = 31 * result + name.hashCode()
                result = 31 * result + instructor.hashCode()
                return result
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Timetable

        if (date != other.date) return false
        if (module != other.module) return false
        if (!events.contentEquals(other.events)) return false
        if (!courses.contentEquals(other.courses)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = date.hashCode()
        result = 31 * result + (module?.hashCode() ?: 0)
        result = 31 * result + events.contentHashCode()
        result = 31 * result + courses.contentHashCode()
        return result
    }
}
