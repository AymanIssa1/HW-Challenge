package ayman.issa.hostelworld.features.property_details.data.mappers

import ayman.issa.hostelworld.common.convertPrice
import ayman.issa.hostelworld.common.getCurrencySign
import ayman.issa.hostelworld.common.getRatingDescription
import ayman.issa.hostelworld.common.util.FacilityCategoriesEnum
import ayman.issa.hostelworld.features.property_details.domain.model.FacilitiesGroup
import ayman.issa.hostelworld.features.property_details.domain.model.Facility
import ayman.issa.hostelworld.features.property_details.domain.model.FreeCancellation
import ayman.issa.hostelworld.features.property_details.domain.model.PropertyDetailsUiModel
import ayman.issa.hostelworld.features.property_details.domain.model.RatingBreakdown
import ayman.issa.hostelworld.remote.models.PropertiesResponse
import kotlin.math.min

fun PropertiesResponse.Property.toPropertyDetailUi(
    currency: String,
    rate: Double
): PropertyDetailsUiModel {
    return PropertyDetailsUiModel(
        id = id,
        name = name,
        about = overview,
        imagesGallery = imagesGallery.map { image -> "https://${image.prefix}${image.suffix}" },
        overallRating = (overallRating.overall / 10.0).toFloat(),
        numberOfRatings = overallRating.numberOfRatings,
        descriptionAndNumberOfRatings = "${getRatingDescription((overallRating.overall / 10.0).toFloat())} (${overallRating.numberOfRatings})",
        type = type.toLowerCase().capitalize(),
        distanceFromCityCentre = "${distance.value} ${distance.units}",
        startingPrice = convertPrice(
            getStartingPrice(
                lowestPrivatePricePerNight.value.toDouble(),
                lowestDormPricePerNight?.value?.toDouble()
            ),
            rate
        ).toString(),
        currencySign = getCurrencySign(currency),
        currency = currency,
        freeCancellation = FreeCancellation(
            label = freeCancellation.label,
            description = freeCancellation.description
        ),
        address = "$address1, $address2",
        latitude = latitude,
        longitude = longitude,
        headerFacilities = facilities.find { it.id == FacilityCategoriesEnum.FACILITYCATEGORYFREE.name }
            ?.facilities
            ?.map { facility ->
                Facility(
                    id = facility.id,
                    name = facility.name
                )
            } ?: listOf(),
        facilitiesGroup = facilities.map { facility ->
            val header = facility.name
            val facilities = facility.facilities.map { innerFacility ->
                Facility(
                    id = innerFacility.id,
                    name = innerFacility.name
                )
            }
            FacilitiesGroup(
                groupTitle = header,
                facilities = facilities
            )
        },
        ratingBreakdown = RatingBreakdown(
            value = ratingBreakdown.value / 10f,
            security = ratingBreakdown.security / 10f,
            atmosphere = ratingBreakdown.`fun` / 10f,
            clean = ratingBreakdown.clean / 10f,
            staff = ratingBreakdown.staff / 10f,
            location = ratingBreakdown.location / 10f,
            facilities = ratingBreakdown.facilities / 10f,
        )
    )
}

private fun getStartingPrice(
    lowestPrivatePricePerNight: Double?,
    lowestDormPricePerNight: Double?
): Double {
    if (lowestDormPricePerNight == null)
        return lowestPrivatePricePerNight!!
    if (lowestPrivatePricePerNight == null)
        return lowestDormPricePerNight
    return min(lowestPrivatePricePerNight, lowestDormPricePerNight)
}