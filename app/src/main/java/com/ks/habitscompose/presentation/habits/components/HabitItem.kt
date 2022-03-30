package com.ks.habitscompose.presentation.habits.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Casino
import androidx.compose.material.icons.outlined.Cottage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ks.habitscompose.domain.model.Habit
import com.ks.habitscompose.ui.theme.*

@Composable
fun HabitItem(
    habit: Habit,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    currentWeek: List<Int>,
    today: Int
) {
    Box(modifier = modifier) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Cottage,
                    contentDescription = "uhh",
                    tint = GrayishWhite
                )

                Column {

                    Text(
                        text = habit.name,
                        style = Typography.h6,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = habit.description,
                        style = Typography.body1,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Edit habit",
                        tint = GrayishWhite
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                var color: Color

                for (day in currentWeek) {

                    /**
                     * Cherry - other day with task active
                     * DimCherry - other day without task active
                     * LightGray - today with task active
                     * Gray - today without task active
                     */

                    if(day == today) color = LightGray
                    else color = Cherry

                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                Log.d("OOOOOOOOOOOOOO", "day: $day")
                            }
                            .border(
                                width = 1.dp,
                                color = color
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = day.toString(), color = GrayishWhite)
                    }
                }
            }
        }
    }
}