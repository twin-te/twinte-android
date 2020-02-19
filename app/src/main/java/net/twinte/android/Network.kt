package net.twinte.android

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.twinte.android.types.Calendar
import net.twinte.android.types.Error
import net.twinte.android.types.Period
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

const val API_SCHEME = "https"
const val API_HOST = "dev.api.twinte.net"
const val API_VERSION = "v1"

class HttpError(val name: String, msg: String, val code: Int) : Exception(msg)

object Network {
    val httpClient = OkHttpClient.Builder().cookieJar(WebViewCookieJar()).build()
    val gson = Gson()

    data class SimpleResponse(val body: String, val code: Int)

    private fun apiUrl(path: String) =
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
        throw HttpError(err.name, err.msg, res.code)
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
            Log.d("WIDGET", e.code.toString())
            if (e.code == 401)
                false
            else
                throw e
        }


    /**
     * 指定した日の学年暦を取得
     */
    suspend fun fetchCalender(date: String): Calendar =
        get("/school-calender/${date}").run {
            gson.fromJson<Calendar>(body, Calendar::class.java)
        }

    /**
     * 指定した日の時間割を取得
     */
    suspend fun fetchTimetable(date: String): Array<Period> =
        get("/timetables/?date=${date}").run {
            gson.fromJson<Array<Period>>(body, Array<Period>::class.java)
        }
}
