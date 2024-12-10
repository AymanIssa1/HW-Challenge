package ayman.issa.hostelworld.design_system.core.spacer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
public fun Spacer(
    modifier: Modifier = Modifier,
    horizontal: Dp = 0.dp,
    vertical: Dp = 0.dp
) {
    Box(
        modifier = modifier.size(
            height = vertical,
            width = horizontal
        ),
        content = {}
    )
}
