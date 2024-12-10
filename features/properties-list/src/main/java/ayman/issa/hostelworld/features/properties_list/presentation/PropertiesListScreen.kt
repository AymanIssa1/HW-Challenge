package ayman.issa.hostelworld.features.properties_list.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun PropertiesListScreen(onNavigateDetailScreen: (propertyId: Int, currency: String) -> Unit) {

    val viewModel: PropertiesListViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    PropertiesListContent(
        viewModel = viewModel,
        state = state,
        onNavigateDetailScreen = onNavigateDetailScreen
    )
}
