package com.ks.habitscompose.presentation.edit_habit

import androidx.compose.ui.focus.FocusState

sealed class EditHabitEvent {
    data class EditName(val value: String): EditHabitEvent()
    data class EditDescription(val value: String): EditHabitEvent()
    data class ChangeNameFocus(val focusState: FocusState): EditHabitEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): EditHabitEvent()
    object SaveHabit: EditHabitEvent()
    object DeleteHabit: EditHabitEvent()
}