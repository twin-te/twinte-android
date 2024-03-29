package net.twinte.android.datastore.schedule

import android.content.Context
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

class SharedPreferencesScheduleDataStore @Inject constructor(
    @ApplicationContext context: Context,
    private val twinteBackendHttpClient: TwinteBackendHttpClient,
) : ScheduleDataStore {
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
                // TODO: replace `android.util.Log` with Timber
                // Log.d(TAG, "schedule updated $d $res")
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
