package ayman.issa.hostelworld.features.property_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.common.util.Constants
import ayman.issa.hostelworld.features.property_details.domain.model.PropertyDetailsUiModel
import ayman.issa.hostelworld.features.property_details.domain.usecase.PropertyDetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailsViewModel @Inject constructor(
    private val propertyDetailsUseCases: PropertyDetailsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<Response<PropertyDetailsUiModel>>(Response.Loading)
    val state: StateFlow<Response<PropertyDetailsUiModel>> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Response.Loading,
    )

    init {
        val propertyId = savedStateHandle.get<Int>(Constants.PROPERTY_ID) ?: -1
        val currencyType = savedStateHandle.get<String>(Constants.CURRENCY_TYPE) ?: ""
        onEvent(PropertyDetailsEvents.GetPropertyDetails(propertyId, currencyType))
    }

    fun onEvent(event: PropertyDetailsEvents) {
        when (event) {
            is PropertyDetailsEvents.GetPropertyDetails -> {
                viewModelScope.launch {
                    getPropertyDetails(event.propertyId, event.currency)
                }

            }
        }

    }

    private suspend fun getPropertyDetails(propertyId: Int, currency: String) {
        propertyDetailsUseCases.getPropertyDetailsUseCase(propertyId, currency).onEach {
            _state.value = it
        }.launchIn(viewModelScope)
    }

}

