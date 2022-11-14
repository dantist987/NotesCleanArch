package com.example.notescleanarch.data.repository

import com.example.notescleanarch.core.Resource
import com.example.notescleanarch.data.local.NoteDao
import com.example.notescleanarch.data.mapper.toDomain
import com.example.notescleanarch.data.mapper.toEntity
import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun addNote(note: Note): Flow<Resource<Unit>> = flow{
        Resource.Loading(null)
        try {
            val data = noteDao.addNote(note.toEntity())
            Resource.Success(data)
        } catch (ioException: IOException){
            Resource.Error(ioException.localizedMessage?:"Неизвестная ошибка")
        }
    }

    override fun deleteNote(note: Note): Flow<Resource<Unit>> = flow{
        Resource.Loading(null)
        try {
            val data = noteDao.deleteNote(note.toEntity())
            Resource.Success(data)
        } catch (ioException: IOException){
            Resource.Error(ioException.localizedMessage?:"Неизвестная ошибка")
        }
    }

    override fun editNote(note: Note): Flow<Resource<Note>> = flow{
        Resource.Loading(null)
        try {
            val data = noteDao.editNote(note.toEntity())
            Resource.Success(data)
        } catch (ioException: IOException){
            Resource.Error(ioException.localizedMessage?:"Неизвестная ошибка")
        }
    }

    override fun getAllNotes(): Flow<Resource<List<Note>>> = flow{
        Resource.Loading(null)
        try {
            val data = noteDao.getAllNotes()
            Resource.Success(data)
        } catch (ioException: IOException){
            Resource.Error(ioException.localizedMessage?:"Неизвестная ошибка")
        }
    }


}