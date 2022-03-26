package com.ks.habitscompose.domain.use_case

import com.ks.habitscompose.domain.model.Habit
import com.ks.habitscompose.domain.model.InvalidHabitException
import com.ks.habitscompose.domain.repository.HabitsRepository

class AddHabitUseCase(private val repository: HabitsRepository) {

    @Throws(InvalidHabitException::class)
    suspend operator fun invoke(habit: Habit) {
        if(habit.name.isBlank()) {
            throw InvalidHabitException("The name of this habit is empty.")
        }

        repository.insertHabit(habit)
    }
}