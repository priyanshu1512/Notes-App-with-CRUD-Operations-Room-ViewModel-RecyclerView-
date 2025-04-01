package com.example.mynoteapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynoteapp.Data.Note
import com.example.mynoteapp.Data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository: NoteRepository = Graph.noteRepository
):ViewModel() {

    var noteTitleState by mutableStateOf("")
    var noteDescriptionState by mutableStateOf("")


    fun onNoteTitleChanged(newString:String){
        noteTitleState = newString
    }

    fun onNoteDescriptionChanged(newString: String){
        noteDescriptionState = newString
    }

    lateinit var getAllNotes: Flow<List<Note>>

    init {
        viewModelScope.launch {
            getAllNotes = noteRepository.getNotes()
        }
    }

    fun addNote(note : Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addANote(note= note)
        }
    }

    fun getANoteById(id:Long):Flow<Note> {
        return noteRepository.getANoteById(id)
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateANote(note= note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteANote(note= note)
            getAllNotes = noteRepository.getNotes()
        }
    }

}