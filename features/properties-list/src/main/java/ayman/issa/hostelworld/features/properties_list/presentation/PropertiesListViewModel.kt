package ayman.issa.hostelworld.features.properties_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.properties_list.domain.model.PropertyUiModel
import ayman.issa.hostelworld.features.properties_list.domain.usecase.PropertiesListUseCases
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
class PropertiesListViewModel @Inject constructor(
    private val propertiesListUseCases: PropertiesListUseCases,
) : ViewModel() {

    private val _state = MutableStateFlow<Response<List<PropertyUiModel>>>(Response.Loading)
    val state: StateFlow<Response<List<PropertyUiModel>>> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Response.Loading,
    )

    init {
        onEvent(PropertiesListEvents.GetPropertiesList("EUR"))
    }

    fun onEvent(event: PropertiesListEvents) {
        when (event) {
            is PropertiesListEvents.GetPropertiesList -> {
                viewModelScope.launch {
                    getPropertiesList(event.currency)
                }
            }
        }
    }

    suspend fun getPropertiesList(currency: String) {
        propertiesListUseCases.getPropertiesListUseCase(currency).onEach {
            _state.value = it
        }.launchIn(viewModelScope)
    }

}

