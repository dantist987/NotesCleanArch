package com.example.notescleanarch.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notescleanarch.R
import com.example.notescleanarch.databinding.FragmentNotesBinding
import com.example.notescleanarch.domain.model.Note
import com.example.notescleanarch.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val viewModel by viewModels<NotesFragmentViewModel>()
    private val binding by viewBinding(FragmentNotesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabtn.setOnClickListener {
            viewModel.addNote(
                Note(
                    title = Random.nextInt().toString(),
                    description = Random.nextInt().toString(),
                    createdTime = System.currentTimeMillis().toString()
                )
            )
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addnoteState.collect { state ->
                    when (state) {
                        is UIState.Success -> {

                        }
                        is UIState.Loading -> {

                        }
                        is UIState.Error -> {

                        }
                        is UIState.Empty -> {

                        }
                    }
                }
            }
        }
    }
}