package com.example.notescleanarch.domain.repository

import com.example.notescleanarch.domain.model.Note


interface NoteRepository {


    fun addNote(note: Note)


    fun deleteNote(note: Note)


    fun editNote(note: Note): Note


    fun getAllNotes(): List<Note>

}