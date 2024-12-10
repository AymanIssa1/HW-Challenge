package ayman.issa.hostelworld.features.properties_list.presentation

sealed class PropertiesListEvents {
    data class GetPropertiesList(val currency: String) : PropertiesListEvents()
}