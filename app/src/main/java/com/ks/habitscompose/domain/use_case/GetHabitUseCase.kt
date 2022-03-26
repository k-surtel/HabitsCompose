package com.ks.habitscompose.domain.use_case

import com.ks.habitscompose.domain.model.Habit
import com.ks.habitscompose.domain.repository.HabitsRepository

class GetHabitUseCase(private val repository: HabitsRepository) {

    suspend operator fun invoke(id: Int): Habit? {
        return repository.getHabitById(id)
    }
}