package ayman.issa.hostelworld.design_system.core.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ayman.issa.hostelworld.design_system.core.R

internal val HostelWorldFontFamily by lazy {
    FontFamily(
        Font(R.font.poppins_thin, weight = FontWeight.Thin),
        Font(R.font.poppins_extra_light, weight = FontWeight.ExtraLight),
        Font(R.font.poppins_light, weight = FontWeight.Light),
        Font(R.font.poppins_regular, weight = FontWeight.Normal),
        Font(R.font.poppins_medium, weight = FontWeight.Medium),
        Font(R.font.poppins_semi_bold, weight = FontWeight.SemiBold),
        Font(R.font.poppins_bold, weight = FontWeight.Bold),
        Font(R.font.poppins_extra_bold, weight = FontWeight.ExtraBold),
        Font(R.font.poppins_black, weight = FontWeight.Black),
    )
}