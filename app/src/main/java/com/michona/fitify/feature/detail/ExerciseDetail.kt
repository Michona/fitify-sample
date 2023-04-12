import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.michona.fitify.domain.data.ExerciseID
import com.michona.fitify.feature.detail.ExerciseDetailUIModel
import com.michona.fitify.feature.detail.ExerciseDetailViewModel
import com.michona.fitify.ui.common.TitledTopBar
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ExerciseDetail(exerciseID: ExerciseID, modifier: Modifier = Modifier) {
    val viewModel: ExerciseDetailViewModel = koinViewModel(parameters = { parametersOf(exerciseID) })
    val data = viewModel.uiModel.collectAsStateWithLifecycle()

    ExerciseDetail(model = data.value, onBack = viewModel::tryNavigateBack, modifier = modifier)
}

@Composable
fun ExerciseDetail(model: ExerciseDetailUIModel, onBack: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TitledTopBar(title = model.title, onBack = { onBack() }, modifier)
    }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
        ) {
            if (model is ExerciseDetailUIModel.Loaded) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                ) {
                    VideoPlayer(modifier, model.data.instructionVideoUrl)
                }

                Text(
                    text = model.data.instructionsExpanded,
                    modifier = Modifier
                        .padding(12.dp)
                        .padding(top = 10.dp),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}

/* this is a super basic implementation of ExoPlayer. I don't think it was a main point in the task, so I left it like this. */

@Composable
fun VideoPlayer(modifier: Modifier = Modifier, url: String) {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.Builder().setUri(Uri.parse(url)).build())
            prepare()
            playWhenReady = true
            repeatMode = REPEAT_MODE_ALL
        }
    }

    DisposableEffect(
        AndroidView(
            factory = {
                PlayerView(it).also {
                    it.player = exoPlayer
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f),
        ),
    ) {
        onDispose { exoPlayer.release() }
    }
}
