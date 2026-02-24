package dev.kiryao.ktsmobilecmp.auth.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kiryao.ktsmobilecmp.auth.welcome.data.WelcomeMockDataSource.row1
import dev.kiryao.ktsmobilecmp.auth.welcome.data.WelcomeMockDataSource.row2
import dev.kiryao.ktsmobilecmp.auth.welcome.data.WelcomeMockDataSource.row3
import dev.kiryao.ktsmobilecmp.auth.welcome.data.WelcomeMockDataSource.row4
import dev.kiryao.ktsmobilecmp.auth.welcome.data.WelcomeMockDataSource.row5
import dev.kiryao.ktsmobilecmp.auth.welcome.model.CourseName
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.yield
import ktsmobilecmp.composeapp.generated.resources.Res
import ktsmobilecmp.composeapp.generated.resources.continue_button
import ktsmobilecmp.composeapp.generated.resources.welcome_text
import org.jetbrains.compose.resources.stringResource

private const val DELAY_BETWEEN_SCROLL_MS = 16L
private const val SCROLL_DX = 1f
private const val REPEAT_TIMES = 40

@Composable
fun WelcomeScreen(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    WelcomeScreenMultiRow(
        onNavigateToLogin = onNavigateToLogin,
        rows = listOf(row1, row2, row3, row4, row5)
    )
}

@Composable
fun WelcomeScreenMultiRow(
    onNavigateToLogin: () -> Unit,
    rows: List<List<CourseName>>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF151515))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(Res.string.welcome_text),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            rows.forEachIndexed { index, row ->
                val reverse = index % 2 == 1
                WelcomeRow(list = row, reverse = reverse)
            }
        }
        Button(
            onClick = onNavigateToLogin,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(top = 16.dp, bottom =  32.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF12B956))
        ) {
            Text(stringResource(Res.string.continue_button), color = Color.White)
        }
    }
}



@Composable
fun WelcomeRow(
    list: List<CourseName>,
    reverse: Boolean = false,
    modifier: Modifier = Modifier
) {
    if (list.isEmpty()) return
    val repeated = remember(list) {
        List(REPEAT_TIMES) { list }.flatten()
    }
    val initialIndex = (REPEAT_TIMES / 2) * list.size
    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)
    LazyRow(
        state = lazyListState,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy((-13).dp),
        modifier = modifier.fillMaxWidth()
    ) {
        itemsIndexed(repeated, key = { index, _ ->
            index
        }) { _, course ->
            WelcomeCourseCard(courseName = course)
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
    LaunchedEffect(lazyListState, reverse) {
        lazyListState.scrollToItem(initialIndex)
        val dir = if (reverse) SCROLL_DX else -SCROLL_DX
        while (isActive) {
            if (!lazyListState.isScrollInProgress) {
                lazyListState.scroll(androidx.compose.foundation.MutatePriority.Default) {
                    scrollBy(dir)
                }
                val firstVisible = lazyListState.firstVisibleItemIndex
                val totalSize = repeated.size
                val threshold = list.size * 2
                if (firstVisible < threshold || firstVisible > totalSize - threshold) {
                    val relative = firstVisible % list.size
                    val newIndex = initialIndex + relative
                    lazyListState.scrollToItem(newIndex, lazyListState.firstVisibleItemScrollOffset)
                    yield()
                }
            }
            delay(DELAY_BETWEEN_SCROLL_MS)
        }
    }
}

@Composable
fun WelcomeCourseCard(
    courseName: CourseName
) {
    val rotation = if (courseName.highlighted) -15f else 0f
    val backgroundColor = if (courseName.highlighted) Color(0xFF12B956) else Color(0xFF1E1E20)
    val elevation = if (courseName.highlighted) 12.dp else 4.dp
    Card(
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .padding(3.dp)
            .height(40.dp)
            .wrapContentWidth()
            .graphicsLayer { rotationZ = rotation }
            .then(Modifier),
        elevation = CardDefaults.cardElevation(elevation),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(courseName.text),
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}