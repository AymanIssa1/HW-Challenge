package ayman.issa.hostelworld.design_system.core.carroussel

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ayman.issa.hostelworld.design_system.core.clickableIcon
import ayman.issa.hostelworld.design_system.core.theme.HostelWorldColor

public typealias CarrouselItem = @Composable () -> Unit

@Composable
public fun Carrousel(
    items: List<CarrouselItem>,
    modifier: Modifier = Modifier,
    showEdgeItems: Boolean = true,
    showIndicators: Boolean = true,
    startingIndex: Int = 0
) {
    var isDraggingForward by remember {
        mutableStateOf(false)
    }
    var mostVisibleItemIndex by remember(startingIndex) {
        mutableStateOf(startingIndex)
    }

    val listState = rememberLazyListState()
    val dragState = rememberDraggableState(
        onDelta = { delta ->
            isDraggingForward = delta < 0
            listState.dispatchRawDelta(-delta)
        }
    )

    val itemCount by remember {
        derivedStateOf {
            listState.layoutInfo.totalItemsCount
        }
    }

    fun refreshState() {
        listState
            .thresholdItem(isDraggingForward)
            ?.let { mostVisibleItemIndex = it.index }
    }

    suspend fun attachToMostVisibleOne() {
        val hideOffset = mostVisibleItemIndex == 0 || showEdgeItems.not()
        val offset = if (hideOffset) 0 else -50
        listState.animateScrollToItem(
            index = mostVisibleItemIndex,
            scrollOffset = offset
        )
    }

    LaunchedEffect(mostVisibleItemIndex) {
        attachToMostVisibleOne()
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        LazyRow(
            state = listState,
            userScrollEnabled = false,
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.draggable(
                state = dragState,
                orientation = Orientation.Horizontal,
                onDragStopped = {
                    refreshState()
                    attachToMostVisibleOne()
                }
            )
        ) {
            items(items) { item ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillParentMaxWidth(
                            fraction = if (showEdgeItems) 0.9f else 1f
                        )
                        .wrapContentSize()
                ) {
                    item()
                }
            }
        }

        if (showIndicators) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(itemCount) { index ->
                    PageIcon(
                        marked = mostVisibleItemIndex == index,
                        onClicked = { mostVisibleItemIndex = index },
                        modifier = Modifier,
                    )
                }
            }
        }
    }
}

private fun LazyListState.thresholdItem(
    draggingForward: Boolean
): LazyListItemInfo? {
    return layoutInfo.visibleItemsInfo
        .sortedBy { it.offset }
        .let { items ->
            if (draggingForward) items.lastOrNull()
            else items.firstOrNull()
        }
}
@Composable
private fun PageIcon(
    marked: Boolean,
    modifier: Modifier = Modifier,
    onClicked: () -> Unit = { /* Nothing by default */ },
) {
    val (rawBorderColor, rawCircleColor) = if (marked) {
        HostelWorldColor.White to HostelWorldColor.White
    } else {
        HostelWorldColor.LightGray to HostelWorldColor.LightGray
    }

    val animatedContentColor by animateColorAsState(rawCircleColor.color)

    Box(
        modifier = modifier
            .clickableIcon { onClicked() }
            .background(
                color = animatedContentColor,
                shape = CircleShape
            )
            .size(10.dp)
    )
}
