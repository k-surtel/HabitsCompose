package com.ks.habitscompose.dependencies

import android.app.Application
import androidx.room.Room
import com.ks.habitscompose.data.data_source.HabitsDatabase
import com.ks.habitscompose.data.repository.HabitsRepositoryImpl
import com.ks.habitscompose.domain.repository.HabitsRepository
import com.ks.habitscompose.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHabitsDatabase(app: Application): HabitsDatabase {
        return Room.databaseBuilder(
            app,
            HabitsDatabase::class.java,
            HabitsDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideHabitsRepository(database: HabitsDatabase): HabitsRepository {
        return HabitsRepositoryImpl(database.habitsDao)
    }

    @Provides
    @Singleton
    fun provideHabitsUseCases(repository: HabitsRepository): HabitsUseCases {
        return HabitsUseCases(
            getHabitsUseCase = GetHabitsUseCase(repository),
            addHabitUseCase = AddHabitUseCase(repository),
            deleteHabitUseCase = DeleteHabitUseCase(repository),
            getHabitUseCase = GetHabitUseCase(repository)
        )
    }
}