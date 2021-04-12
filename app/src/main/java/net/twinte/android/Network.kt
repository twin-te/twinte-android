package net.twinte.android

import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ConnectionPool
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.Request

const val DOMAIN = "app.twinte.net"
const val SCHEME = "https"

fun TwinteUrlBuilder() = Uri.Builder().scheme(SCHEME).path(DOMAIN)

fun Uri.Builder.buildUrl() = build().toString()

object Network {
    val httpClient = OkHttpClient.Builder()
        .connectTimeout(3000, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .readTimeout(3000, TimeUnit.MILLISECONDS)
        .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
        .cookieJar(WebViewCookieJar).build()

    suspend fun validateGoogleIdToken(idToken: String): Boolean = withContext(Dispatchers.IO) {
        val res = httpClient.newCall(
            Request.Builder()
                .url(
                    TwinteUrlBuilder().appendPath("auth/v3/google/idToken").appendQueryParameter("token", idToken)
                        .buildUrl()
                )
                .build()
        ).execute()
        res.isSuccessful
    }
}
