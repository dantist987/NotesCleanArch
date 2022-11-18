package com.example.notescleanarch.domain.use_case

import com.example.notescleanarch.core.Resource
import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    fun getAllNotes(): Flow<Resource<List<Note>>> = repository.getAllNotes()

}