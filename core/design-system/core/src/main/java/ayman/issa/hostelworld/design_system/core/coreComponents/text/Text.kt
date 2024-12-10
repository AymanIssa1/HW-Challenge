package ayman.issa.hostelworld.design_system.core.coreComponents.text

import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text as MaterialText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import ayman.issa.hostelworld.design_system.core.theme.HostelWorldColor
import ayman.issa.hostelworld.design_system.core.theme.HostelWorldFontFamily


@Composable
public fun Text(
    text: String,
    modifier: Modifier = Modifier,
    color: HostelWorldColor = HostelWorldColor.BlackTextColor,
    options: TextOptions = TextOptions(),
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTextStyle.current
) {
    BaseText(
        text = applyMarkdown(text),
        modifier = modifier,
        color = color,
        options = options,
        maxLines = maxLines,
        fontFamily = HostelWorldFontFamily,
        style = style
    )
}

@Composable
internal fun BaseText(
    text: AnnotatedString,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    color: HostelWorldColor = HostelWorldColor.DarkGray,
    overriddenSize: Dp? = null,
    options: TextOptions = TextOptions(),
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTextStyle.current
) {
    val resultColor by animateColorAsState(color.color)
    val fontSize = overriddenSize?.value?.sp ?: options.size.textUnit

    MaterialText(
        text = text,
        modifier = modifier,
        color = resultColor,
        fontSize = fontSize,
        fontWeight = options.weight.weightUnit,
        textAlign = options.align.textAlign,
        overflow = options.overflow,
        // Default below
        fontFamily = fontFamily,
        letterSpacing = TextUnit.Unspecified,
        fontStyle = FontStyle.Normal,
        lineHeight = fontSize,
        textDecoration = null,
        maxLines = maxLines,
        softWrap = true,
        onTextLayout = { /* Nothing yet */ },
        style = style,
    )
}
