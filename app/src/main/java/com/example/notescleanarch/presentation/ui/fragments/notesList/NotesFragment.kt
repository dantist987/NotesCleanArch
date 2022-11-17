package com.example.notescleanarch.presentation.ui.fragments.notesList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notescleanarch.R
import com.example.notescleanarch.core.BaseFragment
import com.example.notescleanarch.databinding.FragmentNotesBinding
import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.presentation.adapters.NotesAdapter
import com.example.notescleanarch.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment : BaseFragment() {

    private val notesAdapter by lazy { NotesAdapter(this::onItemLongClick) }
    private val viewModel by viewModels<NotesFragmentViewModel>()
    private val binding by viewBinding(FragmentNotesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupRequests()
        setupObservers()
        setupClickListeners()
    }

    private fun initialize() {
        binding.rvNotes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvNotes.adapter = notesAdapter
    }

    private fun setupRequests() {
        viewModel.getAllNotes()
    }

    private fun setupObservers() {

        viewModel.getAllNotesState.collectState(
            onError = {

            },
            onLoading = {

            },
            onSuccess = {

            }
        )


        viewModel.editNoteState.collectState(
            onError = {

            },
            onLoading = {

            },
            onSuccess = {

            }
        )


        viewModel.deleteNoteState.collectState(
            onError = {

            },
            onLoading = {

            },
            onSuccess = {

            }
        )
    }

    private fun setupClickListeners() {
        binding.fabtn.setOnClickListener {
            findNavController().navigate(R.id.newNoteFragment)
        }
    }

    private fun onItemLongClick(note: Note){
        notesAdapter.deleteNote(note)
        viewModel.deleteNote(note)
    }
}
