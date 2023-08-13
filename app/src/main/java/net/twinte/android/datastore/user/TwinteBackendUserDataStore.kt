package net.twinte.android.datastore.user

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.twinte.android.network.TwinteBackendHttpClient
import net.twinte.android.network.params
import javax.inject.Inject

class TwinteBackendUserDataStore @Inject constructor(
    private val twinteBackendHttpClient: TwinteBackendHttpClient,
) : UserDataStore {
    override suspend fun validateGoogleIdToken(idToken: String): Boolean = withContext(Dispatchers.IO) {
        val res = twinteBackendHttpClient.get(
            "/auth/v3/google/idToken",
            params {
                put("token", idToken)
            },
        )
        res.isSuccessful
    }
}
