package net.twinte.android.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.twinte.android.Network
import net.twinte.android.TwinteUrlBuilder
import net.twinte.android.buildUrl
import okhttp3.Request

object UserRepository {
    suspend fun validateGoogleIdToken(idToken: String): Boolean = withContext(Dispatchers.IO) {
        val res = Network.httpClient.newCall(
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
