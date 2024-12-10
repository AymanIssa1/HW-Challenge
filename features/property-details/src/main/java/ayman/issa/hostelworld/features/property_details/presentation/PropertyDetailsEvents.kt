package ayman.issa.hostelworld.features.property_details.presentation

sealed class PropertyDetailsEvents {
    data class GetPropertyDetails(val propertyId: Int, val currency: String) : PropertyDetailsEvents()
}