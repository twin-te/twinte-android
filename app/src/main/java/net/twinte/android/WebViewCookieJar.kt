package net.twinte.android

import android.webkit.CookieManager
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

object WebViewCookieJar : CookieJar {

    val cookieManager = CookieManager.getInstance()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookiesStr = cookieManager.getCookie(url.toUrl().toString()) ?: return emptyList()
        return cookiesStr.split(";").map {
            val r = Regex("(.*?)=(.*)").find(it) ?: return@map null
            Cookie.Builder()
                .name(r.groups[1]?.value?.trim() ?: return@map null)
                .value(r.groups[2]?.value ?: return@map null)
                .domain(DOMAIN)
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
