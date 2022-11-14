package com.example.notescleanarch.domain.repository

import com.example.notescleanarch.core.Resource
import com.example.notescleanarch.domain.model.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository {


    fun addNote(note: Note): Flow<Resource<Unit>>


    fun deleteNote(note: Note): Flow<Resource<Unit>>


    fun editNote(note: Note): Flow<Resource<Note>>


    fun getAllNotes(): Flow<Resource<List<Note>>>

}