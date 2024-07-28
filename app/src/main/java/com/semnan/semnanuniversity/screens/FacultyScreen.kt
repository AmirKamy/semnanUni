package com.semnan.semnanuniversity.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.semnan.semnanuniversity.R
import com.semnan.semnanuniversity.components.FacultyCard
import com.semnan.semnanuniversity.data.model.Faculty
import com.semnan.semnanuniversity.navigation.Screen
import com.semnan.semnanuniversity.viewmodel.MainViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun FacultyScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    faculty: String? = null,
    onUrlClicked: (String) -> Unit
) {

    val scrollState = rememberScrollState()
    viewModel.getFacultyItems()
    val faculties = viewModel.facultyItems.collectAsState().value
    val mainItem = faculties.filter { thisFaculty ->
        thisFaculty.id == faculty
    }

    Column {
        FacultyCard(item = mainItem[0], modifier = Modifier.padding(top = 40.dp)) {}

        Box(modifier = Modifier.verticalScroll(scrollState).weight(1f)){
            Text(
                text = mainItem[0].body,
                style = MaterialTheme.typography.bodyMedium.copy(textDirection = TextDirection.Rtl),
                modifier = Modifier.padding(12.dp),
                lineHeight = 29.sp
            )

        }


        BottomContent(item = mainItem[0], onUrlClicked = onUrlClicked)

    }


}

@Composable
fun BottomContent(modifier: Modifier = Modifier, item: Faculty, onUrlClicked: (String) -> Unit) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { navigateToWebView(item.siteAddress, onUrlClicked) },
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                start = 10.dp,
                top = 0.dp,
                end = 10.dp,
                bottom = 0.dp
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.web),
                contentDescription = "Website",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = stringResource(id = R.string.website_address))
        }
        Button(
            onClick = { openPhoneNumber(item.phone, context) },
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                start = 10.dp,
                top = 0.dp,
                end = 10.dp,
                bottom = 0.dp
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.frame__6_),
                contentDescription = "Website",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = stringResource(id = R.string.phone_number))

        }
        Button(
            onClick = {
          //      uriHandler.openUri(item.map)
                navigateToWebView(item.map, onUrlClicked)
                      },
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                start = 10.dp,
                top = 0.dp,
                end = 10.dp,
                bottom = 0.dp
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.map),
                contentDescription = "Website",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = stringResource(id = R.string.map_address))
        }
    }
}


fun openPhoneNumber(number: String, context: Context) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$number")
    }
    context.startActivity(intent)
}

fun navigateToWebView(url: String, onUrlClicked: (String) -> Unit) {
    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
    onUrlClicked(Screen.WebViewScreen.createRoute(encodedUrl))
}

@Preview
@Composable
fun Preview() {

}