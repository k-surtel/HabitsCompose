package com.ks.habitscompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val description: String
   // val daysActive: List<String>
) {
    companion object {
        val daysOfWeek = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
    }
}

class InvalidHabitException(message: String): Exception(message)
