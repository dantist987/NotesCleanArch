package com.example.notescleanarch.domain.use_case

import com.example.notescleanarch.core.Resource
import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    fun editNote(note: Note): Flow<Resource<Note>> = repository.editNote(note)

}