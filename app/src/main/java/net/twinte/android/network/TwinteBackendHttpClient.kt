package net.twinte.android.network

import okhttp3.Response

interface TwinteBackendHttpClient {
    suspend fun get(path: String, params: QueryParameters = QueryParameters()): Response
}
