package com.ks.habitscompose.presentation.habits

import com.ks.habitscompose.domain.model.Habit
import com.ks.habitscompose.domain.utils.HabitsOrder

sealed class HabitsEvent {
    data class Order(val habitsOrder: HabitsOrder): HabitsEvent()
    data class DeleteHabit(val habit: Habit): HabitsEvent()
    object RestoreHabit: HabitsEvent()
    object ToggleOrderSection: HabitsEvent()
}
