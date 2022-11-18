package com.example.notescleanarch

import android.app.Application
import androidx.room.Room
import com.example.notescleanarch.data.local.Database
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application()
