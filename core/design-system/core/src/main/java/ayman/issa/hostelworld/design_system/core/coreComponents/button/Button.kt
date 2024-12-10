package ayman.issa.hostelworld.design_system.core.coreComponents.button

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import ayman.issa.hostelworld.design_system.core.coreComponents.text.Text
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextAlignment
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextOptions
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextWeight
import ayman.issa.hostelworld.design_system.core.modifyIfTrue

@Composable
public fun Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Brand,
    style: ButtonStyle = ButtonStyle.Default,
) {
    CoreButton(
        variant = variant,
        onClick = onClick,
        enabled = enabled,
        internalPadding = style.outerPadding,
        modifier = modifier,
    ) {
        Text(
            text = text,
            color = variant.textColor,
            options = TextOptions(
                size = style.textSize,
                align = TextAlignment.Center,
                weight = TextWeight.Bold
            ),
        )
    }
}
