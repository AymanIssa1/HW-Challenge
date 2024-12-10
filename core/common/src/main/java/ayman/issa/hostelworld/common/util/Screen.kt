package ayman.issa.hostelworld.common.util

sealed class Screen(val route: String) {
    data object PropertiesListScreen : Screen("properties_list_screen")
    data object PropertyDetailScreen : Screen("property_details_screen")
}
