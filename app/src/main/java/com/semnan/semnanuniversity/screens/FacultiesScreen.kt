package com.semnan.semnanuniversity.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.semnan.semnanuniversity.common.MainItemsEnum
import com.semnan.semnanuniversity.components.FacultyCard
import com.semnan.semnanuniversity.data.model.Faculty
import com.semnan.semnanuniversity.viewmodel.MainViewModel

@Composable
fun FacultiesScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    comeFrom: String?,
    onFacultyItemClick: (String, String) -> Unit = { s: String, s1: String -> }
) {
    if (comeFrom == null) return

    if (comeFrom == MainItemsEnum.DaneshkadeHa.name) {
        viewModel.getFacultyItems()
    } else if (comeFrom == MainItemsEnum.MoavenatHa.name) {
        viewModel.getMoavenatItems()
    }

    val items = viewModel.facultyItems.collectAsState().value

    FacultiesList(
        onItemClick = onFacultyItemClick,
        items = items,
        modifier = modifier,
        comeFrom = comeFrom
    )


}

@Composable
fun FacultiesList(
    modifier: Modifier = Modifier,
    items: List<Faculty>,
    onItemClick: (String, String) -> Unit,
    comeFrom: String
) {

    LazyColumn(
        modifier = modifier.padding(top = 50.dp)
    ) {
        items(items) { item ->
            FacultyCard(item = item, onItemClick = onItemClick, comeFrom = comeFrom)
        }
    }
}


