package net.twinte.android.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.twinte.android.API_PATH
import net.twinte.android.Network
import net.twinte.android.buildUrl
import net.twinte.android.model.Timetable
import net.twinte.android.twinteUrlBuilder
import okhttp3.Request
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ScheduleRepository(context: Context) {
    private val pref = context.getSharedPreferences("schedule_cache", Context.MODE_PRIVATE)
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)
    private val gson = Gson()

    suspend fun update(
        calendar: Array<Date> = arrayOf(
            Calendar.getInstance().time,
            Calendar.getInstance().apply { add(Calendar.DATE, 1) }.time
        ),
    ) = withContext(Dispatchers.IO) {
        with(pref.edit()) {
            clear()
            calendar.map { simpleDateFormat.format(it) }.forEach { d ->
                val res = Network.httpClient.newCall(
                    Request.Builder()
                        .url(twinteUrlBuilder().appendPath(API_PATH).appendPath("/timetable").appendPath(d).buildUrl())
                        .build()
                ).execute()
                if (!res.isSuccessful) {
                    if (res.code == 401) {
                        throw Network.NotLoggedInException()
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

    suspend fun getSchedule(date: Date): Timetable = withContext(Dispatchers.IO) {
        val d = simpleDateFormat.format(date)
        if (!pref.contains(d)) update(arrayOf(date))

        gson.fromJson(pref.getString(d, null), Timetable::class.java)
    }
}
