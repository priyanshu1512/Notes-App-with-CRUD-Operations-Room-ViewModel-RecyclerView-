package com.example.mynoteapp

import android.app.Application

class NoteListApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}