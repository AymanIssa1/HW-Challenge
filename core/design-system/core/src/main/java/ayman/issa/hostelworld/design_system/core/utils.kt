package ayman.issa.hostelworld.design_system.core

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public fun Modifier.modifyIfTrue(
    predicate: Boolean,
    elseAction: Modifier.() -> Modifier = { this },
    action: Modifier.() -> Modifier,
): Modifier {
    return if (predicate) {
        this.action()
    } else {
        elseAction()
    }
}

public fun Modifier.clickableIcon(
    shape: Shape = CircleShape,
    padding: Dp = 8.dp,
    onClick: () -> Unit
): Modifier = clip(shape)
    .clickableSingle { onClick() }
    .padding(padding)

public val statusBarHeight: Dp
    @Composable get() = getDpForResourceName("status_bar_height")

public val navigationBarHeight: Dp
    @Composable get() = getDpForResourceName("navigation_bar_height")

@Composable
private fun getDpForResourceName(name: String): Dp {
    val context = LocalContext.current
    val density = LocalDensity.current
    val resourceId = context.resources
        .getIdentifier(name, "dimen", "android")
    return remember {
        if (resourceId > 0) {
            with(density) {
                context.resources.getDimensionPixelSize(resourceId).toDp()
            }
        } else {
            Dp.Hairline
        }
    }
}
