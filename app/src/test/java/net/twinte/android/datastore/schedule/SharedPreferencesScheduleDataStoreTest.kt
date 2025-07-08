package net.twinte.android.datastore.schedule

import com.connectrpc.ResponseMessage
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import net.twinte.android.network.V4ApiClient
import net.twinte.api.shared.Type.RFC3339FullDate
import net.twinte.api.unified.v1.Service.GetByDateRequest
import net.twinte.api.unified.v1.Service.GetByDateResponse
import net.twinte.api.unified.v1.UnifiedServiceClient
import org.junit.Test
import java.time.LocalDate
import java.util.Date
import java.util.TimeZone

@OptIn(ExperimentalCoroutinesApi::class)
class SharedPreferencesScheduleDataStoreTest {
    @Test
    fun validTimetablePath() = runTest {
        // given
        val unifiedClient = mockk<UnifiedServiceClient>()
        val v4Client = mockk<V4ApiClient>()
        every { v4Client.unified } returns unifiedClient

        val datastore = SharedPreferencesScheduleDataStore(
            mockk(relaxed = true),
            v4Client,
        )

        val targetDate = LocalDate.of(2022, 6, 10)
        val dateSlot = slot<GetByDateRequest>()

        val success = ResponseMessage.Success(
            message = GetByDateResponse.getDefaultInstance(),
            headers = emptyMap(),
            trailers = emptyMap(),
        )
        coEvery { unifiedClient.getByDate(capture(dateSlot)) } returns success

        // when
        datastore.update(
            arrayOf(
                Date.from(
                    targetDate.atStartOfDay(
                        TimeZone.getDefault().toZoneId(),
                    ).toInstant(),
                ),
            ),
        )

        // then
        assert(dateSlot.captured.date == RFC3339FullDate.newBuilder().setValue("2022-06-10").build())
    }
}
