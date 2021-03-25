package net.twinte.android

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.twinte.android.types.*
import net.twinte.android.types.Calendar
import net.twinte.android.new_types.Timetable
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


const val API_SCHEME = "https"
const val API_HOST = "app.twinte.net"
const val API_VERSION = "api/v3"

class HttpError(val name: String, msg: String, val code: Int) : Exception(msg)

object Network {
    val httpClient = OkHttpClient.Builder()
        .connectTimeout(3000, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .readTimeout(3000, TimeUnit.MILLISECONDS)
        .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
        .cookieJar(WebViewCookieJar()).build()
    val gson = Gson()

    data class SimpleResponse(val body: String, val code: Int)

    fun apiUrl(path: String) =
        "${API_SCHEME}://${API_HOST}/${API_VERSION}${if (!path.startsWith("/")) "/" else ""}${path}"

    /**
     * レスポンスをチェックし、ステータスが4xx, 5xxであれば例外を投げる
     * @param res チェックしたいレスポンス
     */
    private fun checkSuccess(res: Response) {
        if (res.isSuccessful || res.isRedirect) return
        val err = gson.fromJson<Error>(
            res.body?.string() ?: throw Exception("本文がありません"),
            Error::class.java
        )
        throw HttpError(err.message, err.errors.joinToString("\n"), res.code)
    }

    /**
     * HTTP GET するだけ
     */
    private suspend fun get(url: String): SimpleResponse = withContext(Dispatchers.IO) {
        val req = Request.Builder().url(apiUrl(url)).build()
        val res = httpClient.newCall(req).execute()
        checkSuccess(res)
        SimpleResponse(res.body?.string() ?: throw Exception("本文がありません"), res.code)
    }

    /**
     * ログイン済みかを判定
     */
    suspend fun isLoggedIn() =
        try {
            get("/users/me")
            true
        } catch (e: HttpError) {
            if (e.code == 401)
                false
            else
                throw e
        }


    /**
     * 指定した日の学年暦を取得
     */
    suspend fun fetchCalender(date: String): Calendar =
        get("/timetable/${date}").run {
            val res = gson.fromJson<Timetable>(body, Timetable::class.java)
            toOldCalendar(res, date)
        }

    /**
     * 指定した日の時間割を取得
     */
    suspend fun fetchTimetable(date: String): Array<Period> =
        get("/timetable/${date}").run {
            val res = gson.fromJson<Timetable>(body, Timetable::class.java)
            toOldPeriod(res, date)
        }

    /**
     * 古いオブジェクトの型に変換
     * 一時対応なので次のアプデで消す
     */
    private fun toOldPeriod(res: Timetable, date: String): Array<Period> {
        val day = res.events.find { it.changeTo != null }?.changeTo ?: Day.valueOf(
            arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")[SimpleDateFormat(
                "yyyy-MM-dd",
                Locale.JAPAN
            ).parse(date).day]
        )
        return (1..6).mapNotNull { period ->
            val targets = res.courses.filter { c ->
                val schedule = c.schedules ?: c.course?.schedules
                schedule?.find { it.module == res.module.module && it.day === day && it.period == period } != null
            }

            if (targets.size == 1) {
                val target = targets[0]
                val schedule = target.schedules ?: target.course?.schedules
                val targetSchedule =
                    schedule?.find { it.module == res.module.module && it.day === day && it.period == period }
                Period(
                    "", target.name ?: target.course?.name ?: "",
                    target.instructor ?: target.course?.instructor ?: "",
                    0,
                    targetSchedule?.module ?: Module.Unknown,
                    targetSchedule?.day ?: Day.Sun,
                    targetSchedule?.period ?: period,
                    targetSchedule?.room ?: "",
                    target.id,
                    target.methods ?: target.course?.methods ?: emptyArray()
                )
            } else if (targets.size > 1)
                Period("", "授業に重複があります", "", 0, Module.Unknown, Day.Sun, period, "", "", emptyArray())
            else null
        }.toTypedArray()
    }

    /**
     * 古いオブジェクトの型に変換
     * 一時対応なので次のアプデで消す
     */
    private fun toOldCalendar(res: Timetable, date: String): Calendar {
        val event = res.events.find { it.eventType != EventType.SubstituteDay }
        val substitute = res.events.find { it.eventType == EventType.SubstituteDay }

        return Calendar(
            if (event != null) Event(event.description, event.eventType, emptyMap(), event.date)
            else null,
            if (substitute != null)
                SubstituteDay(substitute.date, substitute.changeTo ?: Day.Sun)
            else null,
            res.module.module
        )
    }
}
