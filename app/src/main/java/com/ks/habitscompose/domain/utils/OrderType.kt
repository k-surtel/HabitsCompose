package com.ks.habitscompose.domain.utils

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
