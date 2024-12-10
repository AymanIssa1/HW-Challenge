package ayman.issa.hostelworld.design_system.core.coreComponents.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ayman.issa.hostelworld.design_system.core.coreComponents.BackgroundMode
import ayman.issa.hostelworld.design_system.core.modifyIfTrue

@Composable
public fun CoreButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    internalPadding: Dp = 16.dp,
    variant: ButtonVariant = ButtonVariant.Brand,
    content: @Composable RowScope.() -> Unit,
) {
    val buttonShape = RoundedCornerShape(16.dp)
    val buttonModifier = modifier
        .clip(buttonShape)
        .modifyIfTrue(enabled.not()) { alpha(0.5f) }
        .modifyIfTrue(enabled) { clickable { onClick() } }
        .let { updatingModifier ->
            when (variant.backgroundMode) {
                is BackgroundMode.Fading -> updatingModifier.background(
                    brush = variant.backgroundMode.brush,
                    shape = buttonShape
                )

                is BackgroundMode.Solid -> updatingModifier.background(
                    color = variant.backgroundMode.color.color,
                    shape = buttonShape
                )

            }
        }
        .modifyIfTrue(variant.borderColor != null) {
            border(
                width = 2.dp,
                color = variant.borderColor?.color ?: Color.Transparent,
                shape = buttonShape
            )
        }
        .padding(internalPadding)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = buttonModifier,
        content = content
    )
}
