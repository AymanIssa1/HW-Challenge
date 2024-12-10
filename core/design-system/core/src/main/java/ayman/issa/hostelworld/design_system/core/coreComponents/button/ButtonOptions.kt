package ayman.issa.hostelworld.design_system.core.coreComponents.button

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ayman.issa.hostelworld.design_system.core.coreComponents.BackgroundMode
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextSize
import ayman.issa.hostelworld.design_system.core.theme.HostelWorldColor

public enum class ButtonVariant(
    public val textColor: HostelWorldColor,
    internal val backgroundMode: BackgroundMode,
    internal val borderColor: HostelWorldColor? = null,
) {
    Brand(
        textColor = HostelWorldColor.White,
        borderColor = null,
        backgroundMode = BackgroundMode.Solid(HostelWorldColor.SecondaryColor)
    ),

    Primary(
        textColor = HostelWorldColor.White,
        backgroundMode = BackgroundMode.Solid(HostelWorldColor.DarkGray),
        borderColor = null
    ),

    PrimaryVariant(
        textColor = HostelWorldColor.White,
        backgroundMode = BackgroundMode.Solid(HostelWorldColor.MediumGray),
        borderColor = null
    ),

    SecondaryVariant(
        textColor = HostelWorldColor.MediumGray,
        backgroundMode = BackgroundMode.Solid(HostelWorldColor.White),
        borderColor = HostelWorldColor.MediumGray
    ),

    ActionIconBgr(
        textColor = HostelWorldColor.MediumGray,
        backgroundMode = BackgroundMode.Solid(HostelWorldColor.LightGray),
        borderColor = null
    ),
}

public enum class ButtonStyle(
    public val textSize: TextSize,
    internal val outerPadding: Dp,
) {
    Default(
        outerPadding = 12.dp,
        textSize = TextSize.Small
    ),
    Pill(
        outerPadding = 6.dp,
        textSize = TextSize.XxSmall,
    )
}
