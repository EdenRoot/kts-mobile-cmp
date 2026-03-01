package dev.kiryao.ktsmobilecmp.auth.welcome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.kiryao.ktsmobilecmp.auth.welcome.model.CourseName
import dev.kiryao.ktsmobilecmp.ui.theme.KTSMobileTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.yield
import ktsmobilecmp.composeapp.generated.resources.Res
import ktsmobilecmp.composeapp.generated.resources.course_kotlin_development

private const val DELAY_BETWEEN_SCROLL_MS = 16L
private const val SCROLL_DX = 1f
private const val REPEAT_TIMES = 40

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
            CourseCard(courseName = course)
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

@Preview(showBackground = true, name = "WelcomeContinueButton Light")
@Preview(showBackground = true, name = "WelcomeContinueButton Dark", uiMode = 32)
@Composable
fun WelcomeRowPreview() {
    KTSMobileTheme {
        val mockRows = listOf(
                CourseName(Res.string.course_kotlin_development, highlighted = false),
                CourseName(Res.string.course_kotlin_development, highlighted = true)
        )
        Surface(color = MaterialTheme.colorScheme.background) {
            WelcomeRow(list = mockRows)
        }
    }
}