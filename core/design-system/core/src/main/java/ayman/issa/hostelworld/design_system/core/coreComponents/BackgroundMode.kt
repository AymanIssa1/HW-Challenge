package ayman.issa.hostelworld.design_system.core.coreComponents

import androidx.compose.ui.graphics.Brush
import ayman.issa.hostelworld.design_system.core.theme.HostelWorldColor

internal sealed class BackgroundMode {
    data class Solid(val color: HostelWorldColor) : BackgroundMode()
    data class Fading(val brush: Brush) : BackgroundMode()
}
