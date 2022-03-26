package com.ks.habitscompose.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ks.habitscompose.domain.model.Entry
import com.ks.habitscompose.domain.model.Habit

@Database(
    entities = [Habit::class, Entry::class],
    version = 1
)
abstract class HabitsDatabase : RoomDatabase() {
    abstract val habitsDao: HabitsDao

    companion object {
        const val DATABASE_NAME = "habits_database"
    }
}