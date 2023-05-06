package net.twinte.android.repository.user

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.twinte.android.network.TwinteBackendHttpClient
import net.twinte.android.network.params
import javax.inject.Inject

class TwinteBackendUserRepository @Inject constructor(
    private val twinteBackendHttpClient: TwinteBackendHttpClient,
) : UserRepository {
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
