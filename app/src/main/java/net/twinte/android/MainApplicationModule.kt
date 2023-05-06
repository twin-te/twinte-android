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
import net.twinte.android.network.TwinteBackendHttpClient
import net.twinte.android.network.TwinteBackendHttpClientImpl
import net.twinte.android.network.serversettings.ProductionServerSettings
import net.twinte.android.network.serversettings.ServerSettings
import net.twinte.android.repository.schedule.ScheduleRepository
import net.twinte.android.repository.schedule.SharedPreferencesScheduleRepository
import net.twinte.android.repository.schedulenotification.ScheduleNotificationRepository
import net.twinte.android.repository.schedulenotification.SharedPreferencesScheduleNotificationRepository
import net.twinte.android.repository.user.TwinteBackendUserRepository
import net.twinte.android.repository.user.UserRepository

@Module
@InstallIn(SingletonComponent::class)
interface MainApplicationModule {
    @Binds
    fun bindScheduleRepository(
        scheduleRepository: SharedPreferencesScheduleRepository,
    ): ScheduleRepository

    @Binds
    fun bindUserRepository(
        userRepository: TwinteBackendUserRepository,
    ): UserRepository

    @Binds
    fun bindScheduleNotificationRepository(
        scheduleNotificationRepository: SharedPreferencesScheduleNotificationRepository,
    ): ScheduleNotificationRepository

    companion object {

        @Suppress("ForbiddenComment")
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
