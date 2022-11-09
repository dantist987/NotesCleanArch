package com.example.notescleanarch.data.mapper

import com.example.notescleanarch.data.model.NoteEntity
import com.example.notescleanarch.domain.model.Note

fun Note.toEntity() = NoteEntity(
    id,
    title,
    description,
    createdTime
)

fun NoteEntity.toDomain() = Note(
    id,
    title,
    description,
    createdTime
)