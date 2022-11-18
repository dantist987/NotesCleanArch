package com.example.notescleanarch.presentation.ui.fragments.notesList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notescleanarch.R
import com.example.notescleanarch.core.BaseFragment
import com.example.notescleanarch.databinding.FragmentNotesBinding
import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.presentation.adapters.NotesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : BaseFragment() {

    private val notesAdapter by lazy { NotesAdapter(this::onItemLongClick) }
    private val viewModel by viewModels<NotesFragmentViewModel>()
    private val binding by viewBinding(FragmentNotesBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_notes, container, false)

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
            onSuccess = { notes ->
                notesAdapter.addData(notes)
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

    private fun onItemLongClick(note: Note) {
        notesAdapter.deleteNote(note)
        viewModel.deleteNote(note)
    }
}
