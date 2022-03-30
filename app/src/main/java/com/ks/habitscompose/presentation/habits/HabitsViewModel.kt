package com.ks.habitscompose.presentation.habits

import android.util.Log
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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

private const val TAG = "HabitsViewModel"

@HiltViewModel
class HabitsViewModel @Inject constructor(
    private val habitsUseCases: HabitsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(HabitsState(
        weekDays = getDates(),
        today = getToday()
    ))
    val state: State<HabitsState> = _state

    private var getHabitsJob: Job? = null
    var editedHabit: Habit? = null

    private val _eventFlow = MutableSharedFlow<Boolean>()
    val eventFlow = _eventFlow.asSharedFlow()

    init { getHabits(HabitsOrder.Name(OrderType.Ascending)) }

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

    private fun getToday(): Int {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    }

    fun getDates(): List<Int> {
        val calendar = Calendar.getInstance()

        /**
         *  1 - SUNDAY
         *  2 - MONDAY
         *  3 - TUESDAY
         *  4 - WEDNESDAY
         *  5 - THURSDAY
         *  6 - FRIDAY
         *  7- SATURDAY
         */

        var dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        val weekDays = mutableListOf<Int>()

        val mondayFirstDay = true
        if(mondayFirstDay) dayOfWeek -= 1

        calendar.add(Calendar.DATE, -dayOfWeek+1)


        for(i in 0..6) {
            weekDays.add(calendar.get(Calendar.DAY_OF_MONTH))
            calendar.add(Calendar.DATE, 1)
        }

        return weekDays
    }

}