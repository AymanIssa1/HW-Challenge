package ayman.issa.hostelworld.design_system.core.tabSelector

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import ayman.issa.hostelworld.design_system.core.clickableSingle
import ayman.issa.hostelworld.design_system.core.coreComponents.text.Text
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextAlignment
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextOptions
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextSize
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextWeight
import ayman.issa.hostelworld.design_system.core.theme.HostelWorldColor

public data class TabNavigationItem(
    val tabLabel: String,
    val tab: LazyListScope.() -> Unit,
    val index: Int
)

@Composable
public fun TabSelector(
    modifier: Modifier = Modifier,
    items: List<TabNavigationItem>,
    selectedIndex: Int,
    onIndexChanged: (Int) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .background(
                    color = HostelWorldColor.White.color,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            items.forEach { item ->
                val isCurrentItem = items[selectedIndex] == item

                TabWithUnderline(
                    item = item,
                    isCurrentItem = isCurrentItem,
                    onIndexChanged = onIndexChanged
                )
            }
        }
    }
}

@Composable
fun TabWithUnderline(
    item: TabNavigationItem,
    isCurrentItem: Boolean,
    onIndexChanged: (Int) -> Unit
) {
    var textWidth by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .clickable { onIndexChanged(item.index.toInt()) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item.tabLabel,
            color = HostelWorldColor.BlackTextColor,
            options = TextOptions(
                weight = if (isCurrentItem) TextWeight.Bold else TextWeight.Normal,
                size = TextSize.Small,
                align = TextAlignment.Center
            ),
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 16.dp
                )
                .onGloballyPositioned { coordinates ->
                    textWidth = coordinates.size.width
                }

        )

        if (isCurrentItem) {
            Box(
                modifier = Modifier
                    .width(with(LocalDensity.current) { textWidth.toDp() })
                    .padding(horizontal = 4.dp)
                    .height(2.dp)
                    .background(color = HostelWorldColor.BlackTextColor.color)
            )
        }
    }
}