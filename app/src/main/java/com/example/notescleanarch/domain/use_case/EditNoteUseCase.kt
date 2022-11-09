package com.example.notescleanarch.domain.use_case

import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.domain.repository.NoteRepository
import javax.inject.Inject

class EditNoteUseCase@Inject constructor(
    private val repository: NoteRepository
) {

    fun editNote(note: Note) = repository.editNote(note)

}