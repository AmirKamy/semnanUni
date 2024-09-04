package com.semnan.semnanuniversity.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.semnan.semnanuniversity.components.FacultyCard
import com.semnan.semnanuniversity.components.NumberCard
import com.semnan.semnanuniversity.data.model.Number
import com.semnan.semnanuniversity.network.Resource
import com.semnan.semnanuniversity.viewmodel.MainViewModel

@Composable
fun NumbersScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {

    viewModel.getNumbers()
    val numbers = viewModel.numbers.collectAsState().value

    if (numbers is Resource.Success){
        NumbersList(numbers = numbers.value)
    }else if (numbers is Resource.Failure) {
        Log.e("numbers", "error: ${numbers.toString()}")
    }


}

@Composable
fun NumbersList(numbers: List<Number>, modifier: Modifier = Modifier) {

    Log.i("numbers", numbers.toString())

    LazyColumn(
        modifier = modifier.padding(top = 50.dp)
    ) {
        items(numbers) { number ->
            NumberCard(number)
        }
    }

}