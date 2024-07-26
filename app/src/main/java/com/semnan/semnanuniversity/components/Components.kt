package com.semnan.semnanuniversity.components

import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.semnan.semnanuniversity.R
import com.semnan.semnanuniversity.data.model.Faculty
import com.semnan.semnanuniversity.data.model.MainItems
import com.semnan.semnanuniversity.ui.theme.SemnanUniversityTheme
import com.semnan.semnanuniversity.viewmodel.MainViewModel

@Composable
fun ImageCard(
    item: MainItems,
    modifier: Modifier = Modifier,
    onClickItem: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(10.dp)
            .clickable { onClickItem(item.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .width(110.dp)
                    .height(110.dp)
                    .clip(CircleShape)
            )
            Text(
                text = item.text,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 12.dp),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
fun FacultyCard(modifier: Modifier = Modifier,item: Faculty, onItemClick: (String) -> Unit) {
    Card(modifier = modifier
        .padding(12.dp)
        .clickable {
            onItemClick(Gson().toJson(item))
        }
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = "Faculty Image",
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Text(
                text = item.title,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 12.dp, bottom = 3.dp),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ImageCardPreview() {
    FacultyCard(item = Faculty("و برقدانشکده مهندسی کامپیوتر ", "", 5, "", "", "")) {
    }
}