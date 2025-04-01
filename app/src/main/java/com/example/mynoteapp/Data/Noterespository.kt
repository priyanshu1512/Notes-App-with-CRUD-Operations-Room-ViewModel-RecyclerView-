package com.example.mynoteapp.Data

import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun addANote(note:Note){
        noteDao.addANote(note)
    }
    fun getNotes(): Flow<List<Note>> =noteDao.getAllNotes()

    fun getANoteById(id:Long): Flow<Note> {
        return noteDao.getANoteById(id)
    }
    suspend fun updateANote(note:Note){
        noteDao.updateANote(note)
    }
    suspend fun deleteANote(note:Note) {
        return noteDao.deleteANote(note)
    }
}