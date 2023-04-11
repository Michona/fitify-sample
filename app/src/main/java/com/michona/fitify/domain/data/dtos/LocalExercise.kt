package com.michona.fitify.domain.data.dtos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.michona.fitify.domain.data.ExerciseID
import com.michona.fitify.domain.data.PackID

/* Local DTO, should only be used in the local layer. */

@Entity(tableName = "exercise")
data class LocalExercise(
    @PrimaryKey
    val id: ExerciseID,
    val packId: PackID,
    val instructionHints: List<String>,
    val title: String,
)
