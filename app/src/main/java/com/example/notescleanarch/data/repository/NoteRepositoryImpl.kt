package com.example.notescleanarch.data.repository

import com.example.notescleanarch.data.local.NoteDao
import com.example.notescleanarch.data.mapper.toDomain
import com.example.notescleanarch.data.mapper.toEntity
import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun addNote(note: Note) {
        noteDao.addNote(note.toEntity())
    }

    override fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toEntity())
    }

    override fun editNote(note: Note): Note {
        return noteDao.editNote(note.toEntity()).toDomain()
    }

    override fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes().map { it.toDomain() }
    }


}