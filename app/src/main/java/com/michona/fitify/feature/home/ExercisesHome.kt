package com.michona.fitify.feature.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.michona.fitify.R
import com.michona.fitify.domain.data.Exercise
import com.michona.fitify.navigation.Destination
import com.michona.fitify.ui.common.LoadingContent
import com.michona.fitify.ui.theme.FitifyTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun ExercisesHome(exerciseViewModel: ExercisesViewModel = koinViewModel(), onDetailClicked: (Destination.ExerciseDetail) -> Unit) {
    val data = exerciseViewModel.data.collectAsStateWithLifecycle()
    val isLoading = exerciseViewModel.isLoading.collectAsStateWithLifecycle()

    ExercisesHome(
        data = data.value,
        query = exerciseViewModel.exerciseQuery,
        onQueryUpdate = exerciseViewModel::updateQuery,
        isLoading = isLoading.value,
        onRefresh = exerciseViewModel::refresh,
        onDetailClicked = onDetailClicked
    )
}

@Composable
private fun ExercisesHome(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false, // TODO: default values?
    query: String,
    data: List<Exercise> = listOf(), // TODO: rename
    onRefresh: () -> Unit = {},
    onQueryUpdate: (String) -> Unit,
    onDetailClicked: (Destination.ExerciseDetail) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.content_descr_logo),
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )

        /* todo: should be moved in a reusable container for text fields.  */
        OutlinedTextField(
            value = query,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Search") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.onPrimary,
                textColor = MaterialTheme.colors.onPrimary,
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.primary
            ),
            onValueChange = {
                onQueryUpdate(it)
            }
        )

        LoadingContent(
            isLoading = isLoading, isEmpty = data.isEmpty(), onRefresh = onRefresh,
            emptyContent = {
                EmptyContent(modifier)
            },
            content = {
                /* The exercise list */
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = modifier
                ) {
                    items(data) { exercise ->
                        ExerciseItem(
                            modifier = modifier,
                            data = exercise,
                            onClick = {
                                onDetailClicked(Destination.ExerciseDetail(packCode = "pack--$exercise", exerciseCode = "ex--$exercise"))
                            }
                        )
                    }
                }
            },
            modifier = modifier
        )
    }
}

@Composable
private fun EmptyContent(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "No Exercises Found!", modifier = modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun ExerciseItem(modifier: Modifier = Modifier, data: Exercise, onClick: (Exercise) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onClick(data)
            }
    ) {
        AsyncImage(
            model = "https://static.gofitify.com/exercises/kettlebell/thumbnails/kb001_catchers_squat_and_press.jpg",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(4.dp)
                .size(width = 60.dp, height = 52.dp)
                .clip(MaterialTheme.shapes.medium),
        )
        Text(
            text = data.name,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(
                start = 10.dp
            ),
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun EmptyPreview() {
    FitifyTheme {
        EmptyContent()
    }
}


@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    FitifyTheme {
        // TODO : ?
//        ExercisesHome(data = listOf(), isLoading = false, onDetailClicked = {})
    }
}

