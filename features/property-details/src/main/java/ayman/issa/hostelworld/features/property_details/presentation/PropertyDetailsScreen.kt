package ayman.issa.hostelworld.features.property_details.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun PropertyDetailsScreen(propertyId: Int, currencyType: String, onNavigateBack: () -> Unit) {

    val viewModel: PropertyDetailsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    PropertyDetailsContent(
        state = state,
        onNavigateBack = onNavigateBack,
    )
}
