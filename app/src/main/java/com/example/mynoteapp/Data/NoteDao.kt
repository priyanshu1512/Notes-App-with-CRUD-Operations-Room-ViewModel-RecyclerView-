package com.example.mynoteapp.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addANote(noteEntity: Note)

    // Loads all wishes from the wish table
    @Query("Select * from `note-table`")
    abstract fun getAllNotes(): Flow<List<Note>>

    @Update
    abstract suspend fun updateANote(noteEntity: Note)

    @Delete
    abstract suspend fun deleteANote(noteEntity: Note)

    @Query("Select * from `note-table` where id=:id")
    abstract fun getANoteById(id:Long): Flow<Note>


}