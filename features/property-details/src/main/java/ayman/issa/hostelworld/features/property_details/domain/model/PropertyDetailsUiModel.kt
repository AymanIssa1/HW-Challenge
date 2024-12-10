package ayman.issa.hostelworld.features.property_details.domain.model

data class PropertyDetailsUiModel(
    val id: Int,
    val name: String,
    val imagesGallery: List<String>,
    val overallRating: Float,
    val numberOfRatings: String,
    val descriptionAndNumberOfRatings: String,
    val type: String,
    val about: String,
    val headerFacilities: List<Facility>,
    val facilitiesGroup: List<FacilitiesGroup>,
    val freeCancellation: FreeCancellation,
    val distanceFromCityCentre:String,
    val startingPrice: String,
    val currencySign: String,
    val currency: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val ratingBreakdown: RatingBreakdown
)

data class FreeCancellation(
    val label: String,
    val description: String
)

data class FacilitiesGroup(
    val groupTitle: String,
    val facilities: List<Facility>
)

data class Facility(
    val id: String,
    val name: String
)

data class RatingBreakdown(
    val value: Float = 0f,
    val security: Float = 0f,
    val atmosphere: Float = 0f,
    val clean: Float = 0f,
    val staff: Float = 0f,
    val location: Float = 0f,
    val facilities: Float = 0f,
)

