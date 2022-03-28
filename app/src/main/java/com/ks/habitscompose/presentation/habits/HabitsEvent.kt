package com.ks.habitscompose.presentation.habits

import com.ks.habitscompose.domain.utils.HabitsOrder

sealed class HabitsEvent {
    data class Order(val habitsOrder: HabitsOrder): HabitsEvent()
    object RestoreHabit: HabitsEvent()
    object ToggleOrderSection: HabitsEvent()
}
