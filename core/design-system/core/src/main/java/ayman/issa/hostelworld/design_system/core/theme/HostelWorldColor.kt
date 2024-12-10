package ayman.issa.hostelworld.design_system.core.theme

import androidx.compose.ui.graphics.Color

public sealed class HostelWorldColor(
    val color: Color,
) {
    object BlackTextColor : HostelWorldColor(
        color = Color(0xFF121417),
    )

    object GrayTextColor : HostelWorldColor(
        color = Color(0xFF636C7D),
    )

    object FacilityIconColor : HostelWorldColor(
        color = Color(0xFF2767E7),
    )

    object FacilityIconBackgroundColor : HostelWorldColor(
        color = Color(0xFFF0F3FB),
    )

    object SecondaryColor : HostelWorldColor(
        color = Color(0xFFF05621),
    )

    object White : HostelWorldColor(
        color = Color(0xfff5f6f8),
    )


    object LightBackground : HostelWorldColor(
        color = Color(0xFFFFFFFF),
    )

    object Border : HostelWorldColor(
        color = Color(0xFFC6CFE7),
    )

    object LightGray : HostelWorldColor(
        color = Color(0x80FFFFFF),
    )

    object FadedLightGray : HostelWorldColor(
        color = Color(0xffedf5f5),
    )

    object ChipBackground : HostelWorldColor(
        color = Color(0xfff5f6f8),
    )

    object MediumGray : HostelWorldColor(
        color = Color(0xff324857),
    )

    object MediumGrayShade : HostelWorldColor(
        color = Color(0xffe9ecf0),
    )

    object DarkGray : HostelWorldColor(
        color = Color(0xff262b35),
    )

    object Red : HostelWorldColor(
        color = Color(0xffed1c24),
    )

    object Success : HostelWorldColor(
        color = Color(0xff27ae60),
    )

    object Yellow : HostelWorldColor(
        color = Color(0xffeda81e),
    )

    object Blue : HostelWorldColor(
        color = Color(0xff2d9cdb),
    )

    object Magenta : HostelWorldColor(
        color = Color(0xFFCC0074),
    )

    object Transparent : HostelWorldColor(
        color = Color.Transparent,
    )

    data class Custom(
        val customColor: Color
    ) : HostelWorldColor(
        color = customColor,
    )
}
