package com.example.notescleanarch.domain.model

data class Note(
    val id: Int = 0,
    val title: String,
    val description: String,
    val createdTime: Long
)
