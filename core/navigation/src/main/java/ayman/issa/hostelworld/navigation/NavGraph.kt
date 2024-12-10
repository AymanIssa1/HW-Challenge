package ayman.issa.hostelworld.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ayman.issa.hostelworld.common.util.Screen
import ayman.issa.hostelworld.features.properties_list.presentation.navigation.propertiesScreen
import ayman.issa.hostelworld.features.property_details.presentation.navigation.propertyDetailsScreen

private const val TRANSITION_DURATION = 400

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.PropertiesListScreen.route,
        enterTransition = {
            fadeIn(
                animationSpec = tween(TRANSITION_DURATION)
            )
        }, exitTransition = {
            fadeOut(
                animationSpec = tween(TRANSITION_DURATION)
            )
        }
    ) {
        propertiesScreen(navController)
        propertyDetailsScreen(navController)
    }
}

