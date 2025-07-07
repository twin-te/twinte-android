package net.twinte.android.network

import android.webkit.CookieManager
import okhttp3.ConnectionPool
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

class TwinteBackendHttpClientImpl(
    private val scheme: String,
    private val host: String,
    cookieManager: CookieManager,
) : TwinteBackendHttpClient {
    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(3000, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .readTimeout(3000, TimeUnit.MILLISECONDS)
        .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
        .cookieJar(WebViewCookieJar(cookieManager)).build()

    private fun baseUrl() = HttpUrl
        .Builder()
        .scheme(scheme)
        .host(host)

    private fun HttpUrl.Builder.path(path: String) = apply {
        path.split("/").forEach { addEncodedPathSegment(it) }
    }

    private fun HttpUrl.Builder.query(params: QueryParameters) = apply {
        params.entries.forEach { (key, value) -> addQueryParameter(key, value) }
    }

    private fun Request.Builder.url(path: String, params: QueryParameters): Request.Builder =
        url(baseUrl().path(path).query(params).build())

    override suspend fun get(path: String, params: QueryParameters): Response =
        httpClient.newCall(
            Request
                .Builder()
                .url(path, params)
                .method("GET", null)
                .build(),
        ).execute()

    class WebViewCookieJar(

        private val cookieManager: CookieManager,
    ) : CookieJar {
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
}
