package com.ks.habitscompose.presentation.habits

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ks.habitscompose.domain.model.Habit
import com.ks.habitscompose.domain.use_case.HabitsUseCases
import com.ks.habitscompose.domain.utils.HabitsOrder
import com.ks.habitscompose.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitsViewModel @Inject constructor(
    private val habitsUseCases: HabitsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(HabitsState())
    val state: State<HabitsState> = _state

    private var getHabitsJob: Job? = null
    private var deletedHabit: Habit? = null

    init {
        getHabits(HabitsOrder.Name(OrderType.Ascending))
    }

    fun onEvent(event: HabitsEvent) {
        when(event) {
            is HabitsEvent.Order -> {
                if(state.value.habitsOrder::class == event.habitsOrder::class &&
                        state.value.habitsOrder.orderType == event.habitsOrder.orderType) {
                    return
                }

                getHabits(event.habitsOrder)
            }
            is HabitsEvent.DeleteHabit -> {
                viewModelScope.launch {
                    habitsUseCases.deleteHabitUseCase(event.habit)
                    deletedHabit = event.habit
                }
            }
            is HabitsEvent.RestoreHabit -> {
                viewModelScope.launch {
                    habitsUseCases.addHabitUseCase(deletedHabit ?: return@launch)
                    deletedHabit = null
                }
            }
            is HabitsEvent.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getHabits(habitsOrder: HabitsOrder) {
        getHabitsJob?.cancel()
        getHabitsJob = habitsUseCases.getHabitsUseCase(habitsOrder).onEach { habits ->
            _state.value = state.value.copy(habits = habits, habitsOrder = habitsOrder)
        }.launchIn(viewModelScope)
    }

}