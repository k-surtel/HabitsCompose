package com.ks.habitscompose.presentation.utils

sealed class Screen(val route: String) {
    object HabitsScreen: Screen("habits_screen")
    object EditHabitScreen: Screen("edit_habit_screen")
}
