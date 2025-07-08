package net.twinte.android.network

import net.twinte.api.schoolcalendar.v1.SchoolCalendarServiceClient
import net.twinte.api.timetable.v1.TimetableServiceClient
import net.twinte.api.unified.v1.UnifiedServiceClient

interface V4ApiClient {
    val timetable: TimetableServiceClient
    val schoolCalendar: SchoolCalendarServiceClient
    val unified: UnifiedServiceClient
}
