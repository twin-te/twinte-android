package net.twinte.android.new_types

import net.twinte.android.types.Day
import net.twinte.android.types.EventType
import net.twinte.android.types.Format
import net.twinte.android.types.Module

/**
 * 新しいAPIに対応するための一時的な型
 * 次のアプデでOpenAPIによる型生成に切り替える
 */
data class Timetable(
    val module: CurrentModule,
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
