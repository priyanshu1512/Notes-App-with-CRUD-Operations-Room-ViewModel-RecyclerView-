package com.example.mynoteapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mynoteapp.Data.Note
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: NoteViewModel,
    navController: NavController
){
    val snackMessage = remember{
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if (id != 0L){
        val wish = viewModel.getANoteById(id).collectAsState(initial = Note(0L, "", ""))
        viewModel.noteTitleState = wish.value.title
        viewModel.noteDescriptionState = wish.value.description
    }else{
        viewModel.noteTitleState = ""
        viewModel.noteDescriptionState = ""
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {AppBarView(title =
    if(id != 0L) stringResource(id = R.string.update_note)
    else stringResource(id = R.string.add_note)
    ) {navController.navigateUp()}
    },

        ) {
        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))

            noteTitleField(label = "Title",
                value = viewModel.noteTitleState,
                onValueChanged = {
                    viewModel.onNoteTitleChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            noteDescriptionField(label = "Description",
                value = viewModel.noteDescriptionState,
                onValueChanged = {
                    viewModel.onNoteDescriptionChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick={
                if(viewModel.noteTitleState.isNotEmpty() &&
                    viewModel.noteDescriptionState.isNotEmpty()){
                    if(id != 0L){
                        viewModel.updateNote(
                            Note(
                                id = id,
                                title = viewModel.noteTitleState.trim(),
                                description = viewModel.noteDescriptionState.trim()
                            )
                        )
                        snackMessage.value = "Note has been updated"
                    }else{
                        //  AddWish
                        viewModel.addNote(
                            Note(
                                title = viewModel.noteTitleState.trim(),
                                description = viewModel.noteDescriptionState.trim())
                        )
                        snackMessage.value = "Note has been created"
                    }
                }else{

                    snackMessage.value = "Enter fields to create a note"
                }
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }

            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF87CEEB), // Sky blue to match your note card
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp
                )
            ){
                Text(
                    text = if (id != 0L) stringResource(id = R.string.update_note)
                    else stringResource(
                        id = R.string.add_note
                    ),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                )
            }

        }
    }

}


@Composable
fun noteTitleField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            // using predefined Color
            textColor = Color.Black,
            // using our own colors in Res.Values.Color
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
        )


    )
}
@Composable
fun noteDescriptionField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth().height(200.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            // using predefined Color
            textColor = Color.Black,
            // using our own colors in Res.Values.Color
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
        )


    )
}



