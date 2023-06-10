package net.twinte.android

import android.content.Context
import android.webkit.CookieManager
import androidx.work.WorkManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.twinte.android.datastore.schedule.ScheduleDataStore
import net.twinte.android.datastore.schedule.SharedPreferencesScheduleDataStore
import net.twinte.android.datastore.schedulenotification.ScheduleNotificationDataStore
import net.twinte.android.datastore.schedulenotification.SharedPreferencesScheduleNotificationDataStore
import net.twinte.android.datastore.user.TwinteBackendUserDataStore
import net.twinte.android.datastore.user.UserDataStore
import net.twinte.android.network.TwinteBackendHttpClient
import net.twinte.android.network.TwinteBackendHttpClientImpl
import net.twinte.android.network.serversettings.ProductionServerSettings
import net.twinte.android.network.serversettings.ServerSettings

@Module
@InstallIn(SingletonComponent::class)
interface MainApplicationModule {
    @Binds
    fun bindScheduleDataStore(
        scheduleDataStore: SharedPreferencesScheduleDataStore,
    ): ScheduleDataStore

    @Binds
    fun bindUserDataStore(
        userDataStore: TwinteBackendUserDataStore,
    ): UserDataStore

    @Binds
    fun bindScheduleNotificationDataStore(
        scheduleNotificationDataStore: SharedPreferencesScheduleNotificationDataStore,
    ): ScheduleNotificationDataStore

    companion object {
        // TODO: リリース版でのみ ProductionServerSettings を inject するように変更する（マルチモジュール化が必要）
        @Provides
        fun provideServerSettings(): ServerSettings = ProductionServerSettings()

        @Provides
        fun provideTwinteBackendHttpClient(
            serverSettings: ServerSettings,
            cookieManager: CookieManager,
        ): TwinteBackendHttpClient = TwinteBackendHttpClientImpl(
            serverSettings.twinteBackendApiEndpointScheme,
            serverSettings.twinteBackendApiEndpointHost,
            cookieManager,
        )

        @Provides
        fun provideCookieManager(): CookieManager = CookieManager.getInstance().apply {
            setAcceptCookie(true)
        }

        @Provides
        fun provideWorkManager(
            @ApplicationContext context: Context,
        ): WorkManager = WorkManager.getInstance(context)
    }
}
