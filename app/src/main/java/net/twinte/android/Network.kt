package net.twinte.android

import android.net.Uri
import net.twinte.android.network.serversettings.ServerSettings

fun twinteUrlBuilder(
    serverSettings: ServerSettings,
): Uri.Builder = Uri
    .Builder()
    .scheme(serverSettings.twinteBackendApiEndpointScheme)
    .path(serverSettings.twinteBackendApiEndpointHost)

fun Uri.Builder.buildUrl() = build().toString()
class NotLoggedInException(cause: Throwable? = null) : Throwable("Not Logged in", cause)
