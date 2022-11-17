package com.example.notescleanarch.presentation.ui.fragments.newNote

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notescleanarch.core.BaseFragment
import com.example.notescleanarch.databinding.FragmentNewNoteBinding
import com.example.notescleanarch.domain.model.Note

class NewNoteFragment : BaseFragment() {

    private val binding by viewBinding(FragmentNewNoteBinding::bind)
    private val viewModel by viewModels<NewNoteFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupRequests()
        setupObservers()
        setupClickListeners()
    }

    private fun initialize() {
        TODO("Not yet implemented")
    }

    private fun setupRequests() {
        TODO("Not yet implemented")
    }

    private fun setupObservers() {

        viewModel.addNoteState.collectState(
            onError = {

            },
            onLoading = {

            },
            onSuccess = {

            }
        )
    }


    private fun setupClickListeners() {
        binding.saveBtn.setOnClickListener {
            viewModel.addNote(
                Note(
                    title = binding.etTitle.text.toString(),
                    description = binding.etNote.text.toString(),
                    createdTime = System.currentTimeMillis()
                )
            )
        }
    }
}