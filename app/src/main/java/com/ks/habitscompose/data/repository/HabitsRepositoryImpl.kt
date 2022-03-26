package com.ks.habitscompose.data.repository

import com.ks.habitscompose.data.data_source.HabitsDao
import com.ks.habitscompose.domain.model.Habit
import com.ks.habitscompose.domain.repository.HabitsRepository
import kotlinx.coroutines.flow.Flow

class HabitsRepositoryImpl(private val habitsDao: HabitsDao) : HabitsRepository {

    override suspend fun insertHabit(habit: Habit) {
        habitsDao.insertHabit(habit)
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitsDao.deleteHabit(habit)
    }

    override suspend fun getHabitById(id: Int): Habit? {
        return habitsDao.getHabitById(id)
    }

    override fun getHabits() = habitsDao.getHabits()
}