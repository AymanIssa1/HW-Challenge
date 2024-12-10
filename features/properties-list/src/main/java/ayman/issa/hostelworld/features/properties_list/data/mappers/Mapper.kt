package ayman.issa.hostelworld.features.properties_list.data.mappers

import ayman.issa.hostelworld.common.convertPrice
import ayman.issa.hostelworld.common.getCurrencySign
import ayman.issa.hostelworld.common.getDiscount
import ayman.issa.hostelworld.common.getRatingDescription
import ayman.issa.hostelworld.common.util.FacilitiesEnum
import ayman.issa.hostelworld.common.util.translateToFacility
import ayman.issa.hostelworld.features.properties_list.domain.model.PropertyUiModel
import ayman.issa.hostelworld.remote.models.PropertiesResponse

fun PropertiesResponse.Property.toPropertyUiModel(currency: String, rate: Double): PropertyUiModel {
    return PropertyUiModel(
        id = id,
        name = name,
        imagesGallery = imagesGallery.map { image -> "https://${image.prefix}${image.suffix}" },
        overallRating = (overallRating.overall / 10.0).toFloat(),
        numberOfRatings = "${getRatingDescription((overallRating.overall / 10.0).toFloat())} (${overallRating.numberOfRatings})",
        type = type.toLowerCase().capitalize(),
        distanceFromCityCentre = "${distance.value} ${distance.units}",
        facilities = facilities.flatMap { it.facilities }
            .filter {
                it.id in listOf(
                    FacilitiesEnum.FREEWIFI.name,
                    FacilitiesEnum.WIFI.name,
                    FacilitiesEnum.TEACOFFEEMAKINGFACILITIES
                )
            }
            .map { translateToFacility(it.id) },
        privateRoomOriginalPrice = convertPrice(originalLowestAveragePrivatePricePerNight.value.toDouble(), rate).toString(),
        privateRoomStartingPrice = convertPrice(lowestPrivatePricePerNight.value.toDouble(), rate).toString(),
        privateRoomDiscountPercentage = getDiscount(
            discountedPrice = lowestPrivatePricePerNight.value.toDouble(),
            originalPrice = originalLowestAveragePrivatePricePerNight.value.toDouble()
        ),
        dormOriginalPrice = originalLowestAverageDormPricePerNight?.value?.let { convertPrice(it.toDouble(), rate).toString() },
        dormStartingPrice = lowestDormPricePerNight?.value?.let { convertPrice(it.toDouble(), rate).toString() },
        dormDiscountPercentage = originalLowestAverageDormPricePerNight?.value?.let {
            getDiscount(
                discountedPrice = lowestDormPricePerNight!!.value.toDouble(),
                originalPrice = originalLowestAverageDormPricePerNight!!.value.toDouble()
            )
        },
        currencySign = getCurrencySign(currency),
        currency = currency,
        isFeatured = isFeatured
    )
}