package ayman.issa.hostelworld.features.properties_list.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ayman.issa.hostelworld.common.util.Screen
import ayman.issa.hostelworld.features.properties_list.presentation.PropertiesListScreen

fun NavGraphBuilder.propertiesScreen(navController: NavController) {
    composable(Screen.PropertiesListScreen.route) {
        PropertiesListScreen(
            onNavigateDetailScreen = { propertyId, currency ->

                navController.navigate(Screen.PropertyDetailScreen.route + "/$propertyId/$currency")
            }
        )
    }
}