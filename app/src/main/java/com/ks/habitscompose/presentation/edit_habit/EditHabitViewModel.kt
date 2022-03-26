package com.ks.habitscompose.presentation.edit_habit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ks.habitscompose.domain.model.Habit
import com.ks.habitscompose.domain.model.InvalidHabitException
import com.ks.habitscompose.domain.use_case.HabitsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditHabitViewModel @Inject constructor(
    private val habitsUseCases: HabitsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var habitId: Int? = null

    private val _name = mutableStateOf(TextFieldState(hint = "Habit name"))
    val name: State<TextFieldState> = _name

    private val _description = mutableStateOf(TextFieldState(hint = "Habit description"))
    val description: State<TextFieldState> = _description

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("habitId")?.let { id ->
            if(id != -1) {
                viewModelScope.launch {
                    habitsUseCases.getHabitUseCase(id)?.also {
                        habitId = it.id
                        _name.value = name.value.copy(
                            text = it.name,
                            isHintVisible = false
                        )
                        _description.value = description.value.copy(
                            text = it.description,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: EditHabitEvent) {
        when(event) {
            is EditHabitEvent.EditName -> {
                _name.value = name.value.copy(
                    text = event.value
                )
            }
            is EditHabitEvent.ChangeNameFocus -> {
                _name.value = name.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            name.value.text.isBlank()
                )
            }
            is EditHabitEvent.EditDescription -> {
                _description.value = description.value.copy(
                    text = event.value
                )
            }
            is EditHabitEvent.ChangeDescriptionFocus -> {
                _description.value = description.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            description.value.text.isBlank()
                )
            }
            is EditHabitEvent.SaveHabit -> {
                viewModelScope.launch {
                    try {
                        habitsUseCases.addHabitUseCase(
                            Habit(habitId, name.value.text, description.value.text)
                        )
                        _eventFlow.emit(UiEvent.SaveHabit)
                    } catch(exception: InvalidHabitException) {
                        _eventFlow.emit(UiEvent.ShowSnackbar(exception.message ?: "An error occured..."))
                    }
                }
            }
        }
    }


    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveHabit: UiEvent()
    }
}