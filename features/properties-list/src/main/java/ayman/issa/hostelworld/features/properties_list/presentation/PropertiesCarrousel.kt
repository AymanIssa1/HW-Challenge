package ayman.issa.hostelworld.features.properties_list.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ayman.issa.hostelworld.common.util.TestTag
import ayman.issa.hostelworld.design_system.core.theme.HostelWorldColor
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PropertyCarrousel(
    images: List<String>,
    modifier: Modifier = Modifier,
    showIndicators: Boolean = true,
    startingIndex: Int = 0
) {
    var mostVisibleItemIndex by rememberSaveable { mutableIntStateOf(startingIndex) }

    val pagerState = rememberPagerState(initialPage = startingIndex)

    LaunchedEffect(pagerState.currentPage) {
        mostVisibleItemIndex = pagerState.currentPage
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        HorizontalPager(
            state = pagerState,
            count = images.size,
            userScrollEnabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) { pageIndex ->
            val image = images[pageIndex]
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
            ) {
                AsyncImage(
                    model = image,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .testTag(TestTag.PropertyGalleryImage),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Hostel Image",
                    placeholder = painterResource(ayman.issa.hostelworld.design_system.core.R.drawable.placeholder)
                )
            }
        }

        if (showIndicators) {
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(images.size) { index ->
                    PageIcon(
                        marked = mostVisibleItemIndex == index,
                        onClicked = {},
                        modifier = Modifier
                            .size(10.dp)
                    )
                }
            }
        }
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
            .clickable { onClicked() }
            .background(
                color = animatedContentColor,
                shape = CircleShape
            )
            .size(10.dp)
    )
}