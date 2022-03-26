package com.ks.habitscompose.domain.use_case

import com.ks.habitscompose.domain.model.Habit
import com.ks.habitscompose.domain.repository.HabitsRepository

class DeleteHabitUseCase(private val repository: HabitsRepository) {

    suspend operator fun invoke(habit: Habit) {
        repository.deleteHabit(habit)
    }
}