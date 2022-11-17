package com.example.notescleanarch.presentation.ui.fragments.notesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notescleanarch.core.BaseViewModel
import com.example.notescleanarch.core.Resource
import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.domain.use_case.AddNoteUseCase
import com.example.notescleanarch.domain.use_case.DeleteNoteUseCase
import com.example.notescleanarch.domain.use_case.EditNoteUseCase
import com.example.notescleanarch.domain.use_case.GetAllNotesUseCase
import com.example.notescleanarch.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesFragmentViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val editNoteUseCase: EditNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()

    private val _editNoteState = MutableStateFlow<UIState<Note>>(UIState.Empty())
    val editNoteState = _editNoteState.asStateFlow()

    private val _deleteNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteNoteState = _deleteNoteState.asStateFlow()

    fun getAllNotes() {
        viewModelScope.launch {
            getAllNotesUseCase.getAllNotes().collectData(_getAllNotesState)
        }
    }


    fun editNote(note: Note) {
        viewModelScope.launch {
            editNoteUseCase.editNote(note).collectData(_editNoteState)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(note).collectData(_deleteNoteState)
        }
    }

}