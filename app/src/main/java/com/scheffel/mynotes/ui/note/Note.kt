package com.scheffel.mynotes.ui.note

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey val id: String = UUID
        .randomUUID().toString(),
    val title: String,
    val content: String
)
