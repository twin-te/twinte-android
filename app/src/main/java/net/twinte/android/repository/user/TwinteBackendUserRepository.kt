package net.twinte.android.repository.user

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.twinte.android.AUTH_PATH
import net.twinte.android.Network
import net.twinte.android.buildUrl
import net.twinte.android.twinteUrlBuilder
import okhttp3.Request
import javax.inject.Inject

class TwinteBackendUserRepository @Inject constructor() : UserRepository {
    override suspend fun validateGoogleIdToken(idToken: String): Boolean = withContext(Dispatchers.IO) {
        val res = Network.httpClient.newCall(
            Request.Builder()
                .url(
                    twinteUrlBuilder().appendPath(AUTH_PATH)
                        .appendPath("/google/idToken")
                        .appendQueryParameter("token", idToken)
                        .buildUrl(),
                )
                .build(),
        ).execute()
        res.isSuccessful
    }
}
