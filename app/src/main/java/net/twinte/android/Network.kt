package net.twinte.android

import android.net.Uri
import android.webkit.CookieManager
import okhttp3.*
import java.util.concurrent.TimeUnit

const val DOMAIN = "app.twinte.net"
const val SCHEME = "https"
const val API_PATH = "/api/v3"
const val AUTH_PATH = "/auth/v3"

fun twinteUrlBuilder(): Uri.Builder = Uri.Builder().scheme(SCHEME).path(DOMAIN)

fun Uri.Builder.buildUrl() = build().toString()

object Network {
    val httpClient = OkHttpClient.Builder()
        .connectTimeout(3000, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .readTimeout(3000, TimeUnit.MILLISECONDS)
        .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
        .cookieJar(WebViewCookieJar).build()

    object WebViewCookieJar : CookieJar {
        val cookieManager: CookieManager = CookieManager.getInstance().apply {
            setAcceptCookie(true)
        }

        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            val cookiesStr = cookieManager.getCookie(url.toUrl().toString()) ?: return emptyList()
            return cookiesStr.split(";").map {
                val r = Regex("(.*?)=(.*)").find(it) ?: return@map null
                Cookie.Builder()
                    .name(r.groups[1]?.value?.trim() ?: return@map null)
                    .value(r.groups[2]?.value ?: return@map null)
                    .domain(url.host)
                    .build()
            }.filterNotNull()
        }

        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            cookies.forEach {
                cookieManager.setCookie(url.toString(), it.toString())
            }
            cookieManager.flush()
        }
    }

    class NotLoggedInException(cause: Throwable? = null) : Throwable("Not Logged in", cause)
}
