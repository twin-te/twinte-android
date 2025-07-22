package net.twinte.android.datastore.user

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import net.twinte.android.network.QueryParameters
import net.twinte.android.network.TwinteBackendHttpClient
import okhttp3.Response
import org.junit.Test

class TwinteBackendUserDataStoreTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun validPath() = runTest {
        // given
        val httpClient = mockk<TwinteBackendHttpClient>()
        val datastore = TwinteBackendUserDataStore(
            httpClient,
        )
        val idToken = "idToken123"
        val pathSlot = slot<String>()
        val argumentSlot = slot<QueryParameters>()
        coEvery { httpClient.get(capture(pathSlot), capture(argumentSlot)) } returns mockk<Response>().also {
            every { it.isSuccessful } returns true
        }

        // when
        datastore.validateGoogleIdToken(idToken)

        // then
        assert(pathSlot.captured == "/auth/v4/google/idToken")
        assert(argumentSlot.captured.getValue("token") == idToken)
    }
}
