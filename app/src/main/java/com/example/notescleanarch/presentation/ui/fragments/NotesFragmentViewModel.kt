package com.example.notescleanarch.presentation.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notescleanarch.core.Resource
import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.domain.use_case.AddNoteUseCase
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
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()

    private val _addNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val addnoteState = _addNoteState.asStateFlow()

    fun getAllNotes(note: Note) {
        viewModelScope.launch {
            getAllNotesUseCase.getAllNotes(note).collect { res ->
                when (res) {
                    is Resource.Loading -> {
                        _getAllNotesState.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null) {
                            _getAllNotesState.value = UIState.Success(res.data)
                        }
                    }
                    is Resource.Error -> {
                        _getAllNotesState.value = UIState.Error(res.message!!)
                    }
                }
            }
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            addNoteUseCase.addNote(note).collect { res ->
                when (res) {
                    is Resource.Loading -> {
                        _addNoteState.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null) {
                            _addNoteState.value = UIState.Success(res.data)
                        }
                    }
                    is Resource.Error -> {
                        _addNoteState.value = UIState.Error(res.message!!)
                    }
                }
            }
        }
    }

}