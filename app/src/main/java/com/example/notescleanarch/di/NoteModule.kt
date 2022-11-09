package com.example.notescleanarch.di

import android.content.Context
import androidx.room.Room
import com.example.notescleanarch.data.local.Database
import com.example.notescleanarch.data.local.NoteDao
import com.example.notescleanarch.data.repository.NoteRepositoryImpl
import com.example.notescleanarch.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NoteModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ): Database = Room.databaseBuilder(
        context,
        Database::class.java,
        "note_db"
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideNoteDao(database: Database) = database.noteDao()

    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepositoryImpl(noteDao)

}