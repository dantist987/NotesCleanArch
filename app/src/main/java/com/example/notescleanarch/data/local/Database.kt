package com.example.notescleanarch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notescleanarch.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}