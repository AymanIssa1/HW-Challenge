package ayman.issa.hostelworld.features.properties_list.domain.model

import ayman.issa.hostelworld.common.util.FacilitiesEnum

data class PropertyUiModel(
    val id: Int,
    val name: String,
    val imagesGallery: List<String>,
    val overallRating: Float,
    val numberOfRatings: String,
    val type: String,
    val isFeatured: Boolean,
    val distanceFromCityCentre:String,
    val facilities: List<FacilitiesEnum>,
    val privateRoomOriginalPrice: String,
    val privateRoomStartingPrice: String,
    val privateRoomDiscountPercentage: Int,
    val dormOriginalPrice: String?,
    val dormStartingPrice: String?,
    val dormDiscountPercentage: Int?,
    val currencySign: String,
    val currency: String
)

