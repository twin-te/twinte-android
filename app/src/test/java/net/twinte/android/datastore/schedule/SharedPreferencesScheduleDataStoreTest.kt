package net.twinte.android.datastore.schedule

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import net.twinte.android.network.TwinteBackendHttpClient
import okhttp3.Response
import org.junit.Test
import java.util.Calendar
import java.util.Date

class SharedPreferencesScheduleDataStoreTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun validTimetablePath() = runTest {
        // given
        val httpClient = mockk<TwinteBackendHttpClient>()
        val datastore = SharedPreferencesScheduleDataStore(
            mockk(relaxed = true),
            httpClient,
        )
        val date = Date.from(
            // month value is zero-based, hence indicating 6th month (June)
            Calendar.getInstance().apply { set(2022, 5, 10) }.toInstant(),
        )
        val pathSlot = slot<String>()
        coEvery { httpClient.get(capture(pathSlot), any()) } returns mockk<Response>(relaxed = true).also {
            every { it.isSuccessful } returns true
        }

        // when
        datastore.update(arrayOf(date))

        // then
        assert(pathSlot.captured == "/api/v3/timetable/2022-06-10")
    }
}
