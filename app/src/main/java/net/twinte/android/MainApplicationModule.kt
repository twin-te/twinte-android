package net.twinte.android

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.twinte.android.repository.schedule.ScheduleRepository
import net.twinte.android.repository.schedule.SharedPreferencesScheduleRepository
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
}
