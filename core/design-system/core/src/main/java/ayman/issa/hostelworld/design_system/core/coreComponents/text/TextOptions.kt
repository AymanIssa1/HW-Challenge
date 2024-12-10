package ayman.issa.hostelworld.design_system.core.coreComponents.text

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

public enum class TextSize(
    public val textUnit: TextUnit
) {
    SuperLarge(100.sp),
    XxLarge(32.sp),
    XLarge(24.sp),
    Large(18.sp),
    Regular(16.sp),
    Small(14.sp),
    XSmall(12.sp),
    XxSmall(10.sp),
    Tiny(8.sp),
    XTiny(6.sp),
    XxTiny(4.sp),
}

public enum class TextWeight(
    internal val weightUnit: FontWeight,
) {
    Thin(FontWeight.Thin),
    ExtraLight(FontWeight.ExtraLight),
    Light(FontWeight.Light),
    Normal(FontWeight.Normal),
    Medium(FontWeight.Medium),
    SemiBold(FontWeight.SemiBold),
    Bold(FontWeight.Bold),
    ExtraBold(FontWeight.ExtraBold),
    Black(FontWeight.Black),
}

public enum class TextAlignment(
    internal val textAlign: TextAlign,
) {
    Start(TextAlign.Left),
    Justify(TextAlign.Justify),
    Center(TextAlign.Center),
    End(TextAlign.End),
}

public data class TextOptions(
    val size: TextSize = TextSize.Regular,
    val weight: TextWeight = TextWeight.Normal,
    val align: TextAlignment = TextAlignment.Start,
    val overflow: TextOverflow = TextOverflow.Clip,
)
