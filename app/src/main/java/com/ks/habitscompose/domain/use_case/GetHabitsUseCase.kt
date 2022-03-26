package com.ks.habitscompose.domain.use_case

import com.ks.habitscompose.domain.model.Habit
import com.ks.habitscompose.domain.repository.HabitsRepository
import com.ks.habitscompose.domain.utils.HabitsOrder
import com.ks.habitscompose.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHabitsUseCase(private val repository: HabitsRepository) {

    operator fun invoke(habitsOrder: HabitsOrder = HabitsOrder.Name(OrderType.Ascending)): Flow<List<Habit>> {
        return repository.getHabits().map { habits ->
            if(habitsOrder is HabitsOrder.Name) {
                when(habitsOrder.orderType) {
                    is OrderType.Ascending -> habits.sortedBy { it.name.lowercase() }
                    is OrderType.Descending -> habits.sortedByDescending { it.name.lowercase() }
                }
            } else habits
        }
    }
}