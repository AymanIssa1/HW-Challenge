package ayman.issa.hostelworld.features.property_details.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ayman.issa.hostelworld.common.util.Constants
import ayman.issa.hostelworld.common.util.Screen
import ayman.issa.hostelworld.features.property_details.presentation.PropertyDetailsScreen

fun NavGraphBuilder.propertyDetailsScreen(navController: NavController) {
    composable(
        route = Screen.PropertyDetailScreen.route + "/{${Constants.PROPERTY_ID}}/{${Constants.CURRENCY_TYPE}}",
        arguments = listOf(
            navArgument(Constants.PROPERTY_ID) { type = NavType.IntType },
            navArgument(Constants.CURRENCY_TYPE) { type = NavType.StringType },
        )
    ) {
        val propertyId = it.arguments?.getInt(Constants.PROPERTY_ID) ?: -1
        val currency = it.arguments?.getString(Constants.CURRENCY_TYPE) ?: ""
        PropertyDetailsScreen(
            propertyId = propertyId,
            currencyType = currency,
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }
}