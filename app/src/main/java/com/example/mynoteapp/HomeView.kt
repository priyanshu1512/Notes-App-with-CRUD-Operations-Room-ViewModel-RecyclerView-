package com.example.mynoteapp


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mynoteapp.Data.DummyNote
import com.example.mynoteapp.Data.Note
import eu.tutorials.mynoteapp.Screen


@Composable
fun HomeView(
    navController: NavController,
    viewModel: NoteViewModel
){
    val context = LocalContext.current
    Scaffold(
        topBar = {AppBarView(title= "Notes", {
            Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()

        })},
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                backgroundColor = Color.Black,
                onClick = {
                    Toast.makeText(context, "FAButton Clicked", Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.AddScreen.route + "/0")
                    // TODO Add Navigation to add screen
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

    ) {
        val notelist = viewModel.getAllNotes.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
            items(notelist.value) { note ->
                NoteItem(
                    note = note,
                    onClick = {
                        val id = note.id
                        navController.navigate(Screen.AddScreen.route + "/$id")
                    },
                    onDeleteClick = {
                        viewModel.deleteNote(note)
                    }
                )
            }
        }
        }
    }




@Composable
fun NoteItem(note: Note, onClick: () -> Unit, onDeleteClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 8.dp, end = 8.dp)
            .clickable { onClick() }
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(12.dp),
                spotColor = Color.Gray.copy(alpha = 0.2f)
            ),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White,
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFFFFF), // Beige
                            Color(0xFFE6F7FF)  // Light sky blue
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = note.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF2D3748)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = note.description,
                    fontSize = 14.sp,
                    color = Color(0xFF4A5568)
                )
            }

            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Note",
                    tint = Color(0xFFE53E3E).copy(alpha = 0.85f)
                )
            }
        }
    }
}