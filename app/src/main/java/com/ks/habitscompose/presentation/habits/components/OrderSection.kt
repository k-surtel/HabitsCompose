package com.ks.habitscompose.presentation.habits.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ks.habitscompose.domain.utils.HabitsOrder
import com.ks.habitscompose.domain.utils.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    habitsOrder: HabitsOrder = HabitsOrder.Name(OrderType.Ascending),
    onOrderChange: (HabitsOrder) -> Unit
) {
    
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {

            DefaultRadioButton(
                text = "Name",
                selected = habitsOrder is HabitsOrder.Name,
                onClick = { onOrderChange(HabitsOrder.Name(habitsOrder.orderType)) }
            )
            
            Spacer(modifier = Modifier.width(8.dp))

//            DefaultRadioButton(
//                text = "Date",
//                selected = habitsOrder is HabitsOrder.Name,
//                onClick = { onOrderChange(HabitsOrder.Name(habitsOrder.orderType)) }
//            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {

            DefaultRadioButton(
                text = "Ascending",
                selected = habitsOrder.orderType is OrderType.Ascending,
                onClick = { onOrderChange(habitsOrder.copy(OrderType.Ascending)) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Descending",
                selected = habitsOrder.orderType is OrderType.Descending,
                onClick = { onOrderChange(habitsOrder.copy(OrderType.Descending)) }
            )
        }
    }
}