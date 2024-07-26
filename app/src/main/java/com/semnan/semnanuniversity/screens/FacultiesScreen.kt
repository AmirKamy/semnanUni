package com.semnan.semnanuniversity.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.gson.Gson
import com.semnan.semnanuniversity.components.FacultyCard
import com.semnan.semnanuniversity.components.ImageCard
import com.semnan.semnanuniversity.data.model.Faculty
import com.semnan.semnanuniversity.viewmodel.MainViewModel

@Composable
fun FacultiesScreen(
    modifier: Modifier = Modifier,
    onFacultyItemClick: (String) -> Unit = {}
) {

    FacultiesList(onItemClick = onFacultyItemClick)



}

@Composable
fun FacultiesList(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
) {

    viewModel.getFacultyItems()
    val faculties = viewModel.facultyItems.collectAsState().value

    LazyColumn(
        modifier = modifier.padding(top = 50.dp)
    ) {
        items(faculties) { item ->
            FacultyCard(item = item, onItemClick = onItemClick)
        }
    }
}


