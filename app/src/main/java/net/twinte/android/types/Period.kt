package net.twinte.android.types

data class Period(
    val lecture_code: String,
    val lecture_name: String,
    val instructor: String,
    val year: Int,
    val module: Module,
    val day: Day,
    val period: Int,
    val room: String,
    val user_lecture_id: String,
    val formats: Array<Format>
)
