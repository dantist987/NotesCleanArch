package com.example.notescleanarch.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notescleanarch.databinding.ItemNoteBinding
import com.example.notescleanarch.domain.model.Note
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter(
    // private val onItemClick: (Note) -> Unit,
    private val onLongClick: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val notes = mutableListOf<Note>()

    fun addNote(new: Note) {
        notes.add(0, new)
        notifyItemInserted(notes.lastIndex)
    }


    fun editNote(new: Note) {
        val index = notes.indexOfFirst { it.createdTime == new.createdTime }
        notes[index] = new
        notifyItemChanged(index)
    }

    fun addData(data: List<Note>) {
        notes.clear()
        notes.addAll(data)

        notifyDataSetChanged()
    }

    fun deleteNote(note: Note) {
        val index = notes.indexOfFirst {
            note == note
        }
        notes.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.onBind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun convertDate(millis: String, dateFormat: String): String {
        val simpleDateFormat = SimpleDateFormat(dateFormat)
        return simpleDateFormat.format(Date(millis)).toString()
    }

    inner class NotesViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(note: Note) {

            binding.tvNote.text = note.title
            binding.tvDate.text = convertDate(note.createdTime.toString(), "dd-MMMM-yyyy hh:mm")

            itemView.setOnClickListener {
                // onItemClick.invoke(note)
            }
            itemView.setOnLongClickListener {
                onLongClick.invoke(note)
                return@setOnLongClickListener true
            }
        }
    }
}