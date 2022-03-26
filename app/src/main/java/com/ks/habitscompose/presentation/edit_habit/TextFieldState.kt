package com.ks.habitscompose.presentation.edit_habit

data class TextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)