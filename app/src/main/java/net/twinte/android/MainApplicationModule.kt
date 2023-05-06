package net.twinte.android

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.twinte.android.repository.ScheduleRepository
import net.twinte.android.repository.SharedPreferencesScheduleRepository
import net.twinte.android.repository.TwinteBackendUserRepository
import net.twinte.android.repository.UserRepository

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
}
