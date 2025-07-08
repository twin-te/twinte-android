package net.twinte.android.network

import android.webkit.CookieManager
import com.connectrpc.ProtocolClientConfig
import com.connectrpc.extensions.GoogleJavaLiteProtobufStrategy
import com.connectrpc.impl.ProtocolClient
import com.connectrpc.okhttp.ConnectOkHttpClient
import com.connectrpc.protocols.NetworkProtocol
import net.twinte.android.network.serversettings.ServerSettings
import net.twinte.api.schoolcalendar.v1.SchoolCalendarServiceClient
import net.twinte.api.timetable.v1.TimetableServiceClient
import net.twinte.api.unified.v1.UnifiedServiceClient
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class V4ApiClientImpl(
    private val serverSettings: ServerSettings,
    private val cookieManager: CookieManager,
) : V4ApiClient {
    private val okHttp = OkHttpClient.Builder()
        .connectTimeout(3, TimeUnit.SECONDS)
        .readTimeout(3, TimeUnit.SECONDS)
        .cookieJar(WebViewCookieJar(cookieManager))
        .build()

    private val protocolClient = ProtocolClient(
        httpClient = ConnectOkHttpClient(okHttp),
        config = ProtocolClientConfig(
            host = "${serverSettings.twinteBackendApiEndpointScheme}://${serverSettings.twinteBackendApiEndpointHost}/api/v4",
            serializationStrategy = GoogleJavaLiteProtobufStrategy(),
            networkProtocol = NetworkProtocol.CONNECT,
        ),
    )

    override val timetable = TimetableServiceClient(protocolClient)
    override val schoolCalendar = SchoolCalendarServiceClient(protocolClient)
    override val unified = UnifiedServiceClient(protocolClient)

    private class WebViewCookieJar(
        private val cm: CookieManager,
    ) : CookieJar {
        override fun loadForRequest(url: HttpUrl): List<Cookie> =
            cm.getCookie(url.toString())
                ?.split(";")
                ?.mapNotNull { raw ->
                    raw.trim().split("=", limit = 2).takeIf { it.size == 2 }?.let { kv ->
                        Cookie.Builder()
                            .name(kv[0])
                            .value(kv[1])
                            .domain(url.host)
                            .build()
                    }
                }
                .orEmpty()

        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            cookies.forEach { cm.setCookie(url.toString(), it.toString()) }
            cm.flush()
        }
    }
}
