package ayman.issa.hostelworld.design_system.core

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.clickableSingle(onClick: () -> Unit): Modifier = composed {
    var clickInProgress by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    clickable(enabled = !clickInProgress) {
        if (!clickInProgress) {
            clickInProgress = true
            onClick()
            coroutineScope.launch {
                delay(500) // Prevent clicks for the next 300 milliseconds
                clickInProgress = false
            }
        }
    }
}
