package com.semnan.semnanuniversity.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semnan.semnanuniversity.R
import com.semnan.semnanuniversity.data.model.Faculty
import com.semnan.semnanuniversity.data.model.MainItems
import com.semnan.semnanuniversity.data.model.Number

@Composable
fun ImageCard(
    item: MainItems,
    modifier: Modifier = Modifier,
    imageModifier: Modifier,
    onClickItem: (String) -> Unit = {},
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
                modifier = imageModifier
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
fun FacultyCard(
    modifier: Modifier = Modifier,
    item: Faculty?,
    comeFrom: String,
    onItemClick: (String, String) -> Unit,
) {

    if (item == null) return

    Card(modifier = modifier
        .padding(12.dp)
        .clickable {
            onItemClick(item.id, comeFrom)
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

@Composable
fun NumberCard(number: Number, modifier: Modifier = Modifier) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {

//            RowWithTittleAnnotatedString(title = stringResource(id = R.string.job_name), value = number.job_name ?: "بدون نام")
//            Divider()
            Text(
                text = number.job_name ?: "",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 10.dp ),
                textAlign = TextAlign.End
            )
            RowWithTittleAnnotatedString(
                title = stringResource(id = R.string.subunit),
                value = number.subunit ?: "بدون زیر واحد"
            )
            Divider()
            RowWithTittleAnnotatedString(
                title = stringResource(id = R.string.numbers),
                value = number.numbers ?: "بدون شماره"
            )
            Divider()
            RowWithTittleAnnotatedString(
                title = stringResource(id = R.string.internal_numbers),
                value = number.internal_numbers ?: "بدون شماره داخلی"
            )

            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Divider()
                    RowWithTittleAnnotatedString(
                        title = stringResource(id = R.string.fax),
                        value = number.fax ?: "بدون فکس"
                    )
                    Divider()
                    RowWithTittleAnnotatedString(
                        title = stringResource(id = R.string.describtion),
                        value = number.describtion ?: "بدون توضیحات"
                    )
                    Divider()
                    RowWithTittleAnnotatedString(
                        title = stringResource(id = R.string.address),
                        value = number.addresses ?: "بدون آدرس"
                    )
                }
            }

            TextButton(
                onClick = { expanded = !expanded },
            ) {
                Row {
                    Icon(
                        if (expanded) Icons.Filled.KeyboardArrowUp else
                            Icons.Filled.KeyboardArrowDown, contentDescription = "Expand Icon"
                    )
                    Text(
                        text = if (expanded) stringResource(id = R.string.show_less) else stringResource(
                            id = R.string.show_more
                        ),
                    )

                }

            }


        }


    }
}

@Composable
fun RowWithTittleAnnotatedString(modifier: Modifier = Modifier, title: String, value: String) {
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp
            )
        ) {
            append("$title: ")
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 13.sp,
                fontWeight = FontWeight.Light
            )
        ) {
            append(value)
        }

    }, modifier = modifier.padding(5.dp), textAlign = TextAlign.End)


}

@Composable
fun SearchBox(
    value: String, // is it work when we pass a object?
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String,
    label: String,
    isFilterVisible: Boolean
) {


    AnimatedVisibility(visible = isFilterVisible, modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 12.dp, end = 12.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            val focusManager = LocalFocusManager.current

            OutlinedTextField(
                value = value,
                onValueChange = { onValueChanged(it) },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onValueChanged("")
                            focusManager.clearFocus()
                        }
                    ) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Clear search"
                        )
                    }
                },
                label = {
                    Text(label)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search,
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focusManager.clearFocus()
                    },
                ),
                modifier = Modifier.fillMaxWidth()
            )


        }
    }
}


@Composable
fun ImageCardPreview() {
    NumberCard(
        number = Number(
            "معاونت آموزشی",
            "پردیس 1",
            "این یک دیسکرایب است",
            "35139652",
            "25125485",
            "555",
            addresses = "پزدیس 1"
        )
    )
}