package com.ks.habitscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ks.habitscompose.presentation.edit_habit.EditHabitScreen
import com.ks.habitscompose.presentation.habits.HabitsScreen
import com.ks.habitscompose.presentation.utils.Screen
import com.ks.habitscompose.ui.theme.HabitsComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitsComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HabitsScreen.route
                    ) {
                        composable(Screen.HabitsScreen.route) {
                            HabitsScreen(navController)
                        }
                        composable(
                            route = Screen.EditHabitScreen.route + "?habitId={habitId}",
                            arguments = listOf(
                                navArgument(
                                    name = "habitId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            EditHabitScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
