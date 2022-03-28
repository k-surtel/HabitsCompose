package com.ks.habitscompose.presentation.edit_habit

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ks.habitscompose.presentation.edit_habit.components.HintTextField
import com.ks.habitscompose.presentation.habits.HabitsEvent
import com.ks.habitscompose.presentation.utils.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun EditHabitScreen(
    navController: NavController,
    viewModel: EditHabitViewModel = hiltViewModel()
) {
    val nameState = viewModel.name.value
    val descriptionState = viewModel.description.value

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is EditHabitViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is EditHabitViewModel.UiEvent.SaveHabit -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(EditHabitEvent.SaveHabit) }
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "Save habit")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Habits Compose", style = MaterialTheme.typography.h4)

                if(!viewModel.isNewHabit) {
                    Button(
                        onClick = {
                            viewModel.onEvent(EditHabitEvent.DeleteHabit)
                            navController.navigateUp()
                        },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Text(text = "Delete habit")
                    }
                }
            }

            HintTextField(
                text = nameState.text,
                hint = nameState.hint,
                onValueChange = {
                    viewModel.onEvent(EditHabitEvent.EditName(it))
                },
                onFocusChange = {
                    viewModel.onEvent(EditHabitEvent.ChangeNameFocus(it))
                },
                isHintVisible = nameState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            HintTextField(
                text = descriptionState.text,
                hint = descriptionState.hint,
                onValueChange = {
                    viewModel.onEvent(EditHabitEvent.EditDescription(it))
                },
                onFocusChange = {
                    viewModel.onEvent(EditHabitEvent.ChangeDescriptionFocus(it))
                },
                isHintVisible = descriptionState.isHintVisible,
                singleLine = false,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}