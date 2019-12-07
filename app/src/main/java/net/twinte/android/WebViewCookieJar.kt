package net.twinte.android

import android.webkit.CookieManager
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class WebViewCookieJar : CookieJar {

    val cookieManager = CookieManager.getInstance()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookiesStr = cookieManager.getCookie(url.toUrl().toString()) ?: return emptyList()
        return cookiesStr.split(";").map {
            val r = Regex("(.*?)=(.*)").find(it) ?: return@map null
            Cookie.Builder()
                .name(r.groups[1]?.value?.trim() ?: return@map null)
                .value(r.groups[2]?.value ?: return@map null)
                .domain("dev.api.twinte.net")
                .build()
        }.filterNotNull()
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieManager.setCookie(url.toUri().toString(), cookies.map { it.toString() }.reduce { acc, s -> acc + s })
    }
}
