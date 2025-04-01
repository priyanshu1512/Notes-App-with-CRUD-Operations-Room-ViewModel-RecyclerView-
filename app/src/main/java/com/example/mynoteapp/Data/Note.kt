package com.example.mynoteapp.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="note-table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name="note-title")
    val title: String="",
    @ColumnInfo(name="note-desc")
    val description:String=""
)

object DummyNote{
    val wishList = listOf(
        Note(title="Google Watch 2",
            description =  "An android Watch"),
        Note(title = "Oculus Quest 2",
            description = "A VR headset for playing games"),
        Note(title = "A Sci-fi, Book",
            description= "A science friction book from any best seller"),
        Note(title = "Bean bag",
            description = "A comfy bean bag to substitute for a chair")
    )
}
