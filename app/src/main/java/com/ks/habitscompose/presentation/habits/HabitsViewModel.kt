package com.ks.habitscompose.presentation.habits

import android.util.Log
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ks.habitscompose.domain.model.Habit
import com.ks.habitscompose.domain.use_case.HabitsUseCases
import com.ks.habitscompose.domain.utils.HabitsOrder
import com.ks.habitscompose.domain.utils.OrderType
import com.ks.habitscompose.presentation.edit_habit.EditHabitEvent
import com.ks.habitscompose.presentation.edit_habit.EditHabitViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HabitsViewModel"

@HiltViewModel
class HabitsViewModel @Inject constructor(
    private val habitsUseCases: HabitsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(HabitsState())
    val state: State<HabitsState> = _state

    private var getHabitsJob: Job? = null
    var editedHabit: Habit? = null

    private val _eventFlow = MutableSharedFlow<Boolean>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getHabits(HabitsOrder.Name(OrderType.Ascending))


    }

    fun onEvent(event: HabitsEvent) {
        when (event) {
            is HabitsEvent.Order -> {
                if (state.value.habitsOrder::class == event.habitsOrder::class &&
                    state.value.habitsOrder.orderType == event.habitsOrder.orderType
                ) {
                    return
                }

                getHabits(event.habitsOrder)
            }
            is HabitsEvent.RestoreHabit -> {
                viewModelScope.launch {
                    habitsUseCases.addHabitUseCase(editedHabit ?: return@launch)
                    editedHabit = null
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

            editedHabit?.let { editedHabit ->
                if(habits.find { it.id == editedHabit.id } == null)
                    _eventFlow.emit(true)

            }


        }.launchIn(viewModelScope)
    }
}