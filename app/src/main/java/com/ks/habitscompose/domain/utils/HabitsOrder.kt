package com.ks.habitscompose.domain.utils

sealed class HabitsOrder(val orderType: OrderType) {
    class Name(orderType: OrderType): HabitsOrder(orderType)

    fun copy(orderType: OrderType): HabitsOrder {
        return when(this) {
            is Name -> Name(orderType)
        }
    }
}
