package net.twinte.android.datastore.schedule

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.connectrpc.Code
import com.connectrpc.ConnectException
import com.connectrpc.getOrThrow
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.twinte.android.NotLoggedInException
import net.twinte.android.mapping.proto.toTimetable
import net.twinte.android.model.Timetable
import net.twinte.android.network.TwinteBackendHttpClient
import net.twinte.android.network.V4ApiClient
import net.twinte.api.shared.Type.RFC3339FullDate
import net.twinte.api.unified.v1.Service.GetByDateRequest
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
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

@RequiresApi(Build.VERSION_CODES.O)
class V4SharedPreferencesScheduleDataStore @Inject constructor(
    @ApplicationContext context: Context,
    private val api: V4ApiClient,
) : ScheduleDataStore {
    private val pref = context.getSharedPreferences("schedule_cache", Context.MODE_PRIVATE)
    private val keyFmt = SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)

    private val apiFmt = DateTimeFormatter.ISO_LOCAL_DATE // for RPC
    private val gson = Gson()

    override suspend fun update(calendar: Array<Date>): Unit = withContext(Dispatchers.IO) {
        val editor = pref.edit().also { it.clear() }

        for (day in calendar) {
            val key = keyFmt.format(day)
            val rpcDate = apiFmt.format(day.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())

            try {
                val reqDate = LocalDate.parse(rpcDate)
                val resp = api.unified.getByDate(
                    GetByDateRequest.newBuilder()
                        .setDate(RFC3339FullDate.newBuilder().setValue(rpcDate).build())
                        .build(),
                ).getOrThrow()

                val timetable = resp.toTimetable(reqDate)
                editor.putString(key, gson.toJson(timetable))
            } catch (e: ConnectException) {
                if (e.code == Code.UNAUTHENTICATED) {
                    throw NotLoggedInException()
                }
                throw IOException("Connect RPC failed: ${e.code}", e)
            } catch (e: IOException) { return@withContext }
        }

        editor.commit()
    }

    override suspend fun getSchedule(date: Date): Timetable? = withContext(Dispatchers.IO) {
        val key = keyFmt.format(date)
        if (!pref.contains(key)) update(arrayOf(date))
        pref.getString(key, null)?.let { gson.fromJson(it, Timetable::class.java) }
    }
}
