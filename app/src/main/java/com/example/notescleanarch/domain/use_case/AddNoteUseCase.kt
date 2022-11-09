package com.example.notescleanarch.domain.use_case

import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    fun addNote(note: Note) = repository.addNote(note)

}