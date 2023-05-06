package net.twinte.android.repository.schedule

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.twinte.android.NotLoggedInException
import net.twinte.android.model.Timetable
import net.twinte.android.network.TwinteBackendHttpClient
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class SharedPreferencesScheduleRepository @Inject constructor(
    @ApplicationContext context: Context,
    private val twinteBackendHttpClient: TwinteBackendHttpClient,
) : ScheduleRepository {
    private val pref = context.getSharedPreferences("schedule_cache", Context.MODE_PRIVATE)
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)
    private val gson = Gson()

    override suspend fun update(calendar: Array<Date>): Unit = withContext(Dispatchers.IO) {
        with(pref.edit()) {
            clear()
            calendar.map { simpleDateFormat.format(it) }.forEach { d ->
                val res = twinteBackendHttpClient.get("/api/v3/timetable/$d")

                if (!res.isSuccessful) {
                    if (res.code == 401) {
                        throw NotLoggedInException()
                    } else {
                        throw IOException("API call failed with code ${res.code}\n ${res.body?.string()}")
                    }
                }
                putString(d, res.body?.string())
                Log.d("ScheduleRepository", "schedule updated $d $res")
            }
            commit()
        }
    }

    override suspend fun getSchedule(date: Date): Timetable = withContext(Dispatchers.IO) {
        val d = simpleDateFormat.format(date)
        if (!pref.contains(d)) update(arrayOf(date))

        gson.fromJson(pref.getString(d, null), Timetable::class.java)
    }
}
