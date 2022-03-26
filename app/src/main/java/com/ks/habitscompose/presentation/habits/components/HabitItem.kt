package com.ks.habitscompose.presentation.habits.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ks.habitscompose.domain.model.Habit

@Composable
fun HabitItem(
    habit: Habit,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {
    Box(modifier = modifier) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)) {
            Text(text = habit.name, style = MaterialTheme.typography.h6, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = habit.description, style = MaterialTheme.typography.body1, maxLines = 3, overflow = TextOverflow.Ellipsis)
        }

        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete habit"
            )
        }

    }
}