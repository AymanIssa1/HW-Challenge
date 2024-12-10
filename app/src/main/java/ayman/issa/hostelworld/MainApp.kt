package ayman.issa.hostelworld

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import ayman.issa.hostelworld.navigation.NavGraph

@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavGraph(
        navController = navController,
    )
}