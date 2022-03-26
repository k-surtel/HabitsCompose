package com.ks.habitscompose.domain.repository

import com.ks.habitscompose.domain.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitsRepository {

    suspend fun insertHabit(habit: Habit)

    suspend fun deleteHabit(habit: Habit)

    suspend fun getHabitById(id: Int): Habit?

    fun getHabits(): Flow<List<Habit>>
}