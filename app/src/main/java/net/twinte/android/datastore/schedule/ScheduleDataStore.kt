package net.twinte.android.datastore.schedule

import net.twinte.android.model.Timetable
import java.util.Calendar
import java.util.Date

interface ScheduleDataStore {
    suspend fun update(
        calendar: Array<Date> = arrayOf(
            Calendar.getInstance().time,
            Calendar.getInstance().apply { add(Calendar.DATE, 1) }.time,
        ),
    )

    suspend fun getSchedule(date: Date): Timetable
}
