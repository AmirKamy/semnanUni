package com.semnan.semnanuniversity.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.gson.Gson
import com.semnan.semnanuniversity.R
import com.semnan.semnanuniversity.components.FacultyCard
import com.semnan.semnanuniversity.data.model.Faculty
import com.semnan.semnanuniversity.viewmodel.MainViewModel

@Composable
fun FacultyScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    jsonFaculty: String? = null
) {

    val facultyItem = Gson().fromJson(jsonFaculty, Faculty::class.java)
    val scrollState = rememberScrollState()

    val ce = ""
    FacultyCard(item = facultyItem, modifier = Modifier.padding(top = 40.dp)){}

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Text(
            text = ce,
            style = MaterialTheme.typography.bodyMedium.copy(textDirection = TextDirection.Rtl),
            modifier = Modifier.padding(12.dp),
            lineHeight = 29.sp
        )
    }


}

@Preview
@Composable
fun Preview() {
    FacultyScreen()
}