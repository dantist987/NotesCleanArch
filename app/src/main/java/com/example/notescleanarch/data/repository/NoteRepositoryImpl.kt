package com.example.notescleanarch.data.repository

import com.example.notescleanarch.core.BaseRepository
import com.example.notescleanarch.data.local.NoteDao
import com.example.notescleanarch.data.mapper.toDomain
import com.example.notescleanarch.data.mapper.toEntity
import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository, BaseRepository() {

    override fun addNote(note: Note) = doRequest {
        noteDao.addNote(note.toEntity())
    }


    override fun deleteNote(note: Note) = doRequest {
        noteDao.deleteNote(note.toEntity())
    }

    override fun editNote(note: Note) = doRequest {
        noteDao.editNote(note.toEntity())
        return@doRequest note
    }

    override fun getAllNotes() = doRequest {
        noteDao.getAllNotes().map { it.toDomain() }
    }

}