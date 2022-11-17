package com.example.notescleanarch.presentation.ui.fragments.newNote

import androidx.lifecycle.viewModelScope
import com.example.notescleanarch.core.BaseViewModel
import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.domain.use_case.AddNoteUseCase
import com.example.notescleanarch.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteFragmentViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
) : BaseViewModel() {

    private val _addNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val addNoteState = _addNoteState.asStateFlow()

    fun addNote(note: Note) {
        viewModelScope.launch {
            addNoteUseCase.addNote(note).collectData(_addNoteState)
        }
    }
}