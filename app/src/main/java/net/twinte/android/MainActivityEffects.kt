package net.twinte.android

import android.content.Context
import android.webkit.CookieManager
import androidx.work.WorkManager
import net.twinte.android.datastore.resetcookiesforsamesite.ResetCookiesForSameSiteDataStore
import net.twinte.android.datastore.schedule.ScheduleDataStore
import net.twinte.android.datastore.schedulenotification.ScheduleNotificationDataStore
import net.twinte.android.datastore.user.UserDataStore
import net.twinte.android.widget.WidgetUpdater
import net.twinte.android.work.UpdateScheduleWorker
import javax.inject.Inject

class MainActivityEffects @Inject constructor(
    private val scheduleDataStore: ScheduleDataStore,
    private val userDataStore: UserDataStore,
    private val scheduleNotificationDataStore: ScheduleNotificationDataStore,
    private val resetCookiesForSameSiteDataStore: ResetCookiesForSameSiteDataStore,
    private val cookieManager: CookieManager,
    private val workManager: WorkManager,
) {
    fun prepareLaunch() {
        UpdateScheduleWorker.scheduleNextUpdate(workManager)
        scheduleNotificationDataStore.schedule()
        resetCookiesForSameSiteIfNeeded()
    }

    suspend fun refreshAfterLaunch(context: Context) {
        updateScheduleIgnoringLoginError()
        WidgetUpdater.updateAllWidget(context)
        WidgetUpdater.scheduleAllIfExists(context)
    }

    fun flushCookies() {
        cookieManager.flush()
    }

    suspend fun refreshOnPause(context: Context) {
        updateScheduleIgnoringLoginError()
        WidgetUpdater.updateAllWidget(context)
    }

    suspend fun submitGoogleIdToken(idToken: String?): Boolean {
        if (idToken == null) {
            return true
        }
        return runCatching {
            userDataStore.validateGoogleIdToken(idToken)
        }.isSuccess
    }

    private fun resetCookiesForSameSiteIfNeeded() {
        if (resetCookiesForSameSiteDataStore.shouldResetCookiesForSameSite) {
            cookieManager.removeAllCookies {}
            resetCookiesForSameSiteDataStore.shouldResetCookiesForSameSite = false
        }
    }

    private suspend fun updateScheduleIgnoringLoginError() {
        kotlin.runCatching {
            scheduleDataStore.update()
        }.onFailure { error ->
            if (error !is NotLoggedInException) {
                // 予期しないエラーは握りつぶすが、起動導線は止めない
            }
        }
    }
}
