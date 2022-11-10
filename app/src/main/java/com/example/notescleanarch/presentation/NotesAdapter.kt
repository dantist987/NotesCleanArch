package com.example.notescleanarch.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notescleanarch.databinding.ItemNoteBinding
import com.example.notescleanarch.domain.model.Note
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter (
    private val onClick: (Note) -> Unit,
    private val onLongClick: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val notes = mutableListOf<Note>()

    fun addTask(new: Note) {
        notes.add(0, new)
        notifyItemInserted(notes.lastIndex)
    }

    fun setSearchResult(newNoteModel: Note){
        notes.clear()
        notes.add(newNoteModel)
        notifyDataSetChanged()
    }

    fun editTask(new: Note) {
        val index = notes.indexOfFirst { it.createdTime == new.createdTime }
        notes[index] = new
        notifyItemChanged(index)
    }

    fun addData(data: List<Note>?) {
        notes.clear()
        data?.let {
            notes.addAll(it)
        }
        notifyDataSetChanged()
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

    fun removeTask(note: Note) {
        val index = notes.indexOfFirst {
            note == note
        }
        notes.removeAt(index)
        notifyItemRemoved(index)
    }

    fun convertDate(millis: String, dateFormat: String): String {
        val simpleDateFormat = SimpleDateFormat(dateFormat)
        return simpleDateFormat.format(Date(millis)).toString()
    }

    inner class NotesViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: Note) {

            binding.tvTask.text = model.title
            binding.tvDate.text = convertDate(model.createdTime, "dd-MMMM-yyyy hh:mm")

            itemView.setOnClickListener {
                onClick(model)
            }
            itemView.setOnLongClickListener {
                onLongClick(model)
                return@setOnLongClickListener true
            }
        }
    }
}