package net.twinte.android

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.twinte.android.widget.TimetableWidget
import okhttp3.OkHttpClient
import okhttp3.Request

object Util {
    private val client = OkHttpClient.Builder().cookieJar(WebViewCookieJar()).build()
    suspend fun isLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        val req = Request.Builder().url("https://dev.api.twinte.net/v1/users/me").build()
        val res = client.newCall(req).execute()

        res.code == 200
    }
}
