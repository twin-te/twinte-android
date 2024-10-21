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
                kotlin.runCatching { twinteBackendHttpClient.get("/api/v3/timetable/$d") }
                    .fold(onSuccess = {
                        if (!it.isSuccessful) {
                            if (it.code == 401) {
                                throw NotLoggedInException()
                            } else {
                                throw IOException("API call failed with code ${it.code}\n ${it.body.string()}")
                            }
                        }
                        putString(d, it.body.string())
                    }, onFailure = {
                        if (it !is IOException) {
                            throw it
                        }
                        return@withContext
                    })
            }
            commit()
        }
    }

    override suspend fun getSchedule(date: Date): Timetable? = withContext(Dispatchers.IO) {
        val d = simpleDateFormat.format(date)
        if (!pref.contains(d)) update(arrayOf(date))

        gson.fromJson(pref.getString(d, null), Timetable::class.java)
    }
}
