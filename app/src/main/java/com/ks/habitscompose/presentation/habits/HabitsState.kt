package com.ks.habitscompose.presentation.habits

import com.ks.habitscompose.domain.model.Habit
import com.ks.habitscompose.domain.utils.HabitsOrder
import com.ks.habitscompose.domain.utils.OrderType

data class HabitsState(
    val habits: List<Habit> = emptyList(),
    val habitsOrder: HabitsOrder = HabitsOrder.Name(OrderType.Ascending),
    val isOrderSectionVisible: Boolean = false
)
