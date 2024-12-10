package ayman.issa.hostelworld.remote.models

data class PropertiesResponse(
    val filterData: FilterData = FilterData(),
    val location: Location = Location(),
    val locationEn: LocationEn = LocationEn(),
    val pagination: Pagination = Pagination(),
    val properties: List<Property> = listOf(),
) {
    data class FilterData(
        val highestPricePerNight: HighestPricePerNight = HighestPricePerNight(),
        val lowestPricePerNight: LowestPricePerNight = LowestPricePerNight()
    ) {
        data class HighestPricePerNight(
            val currency: String = "",
            val value: String = ""
        )

        data class LowestPricePerNight(
            val currency: String = "",
            val value: String = ""
        )
    }

    data class Location(
        val city: City = City(),
        val region: Any? = Any()
    ) {
        data class City(
            val country: String = "",
            val id: Int = 0,
            val idCountry: Int = 0,
            val name: String = ""
        )
    }

    data class LocationEn(
        val city: City = City(),
        val region: Any? = Any()
    ) {
        data class City(
            val country: String = "",
            val id: Int = 0,
            val idCountry: Int = 0,
            val name: String = ""
        )
    }

    data class Pagination(
        val next: String = "",
        val numberOfPages: Int = 0,
        val prev: String = "",
        val totalNumberOfItems: Int = 0
    )

    data class Property(
        val address1: String = "",
        val address2: String = "",
        val distance: Distance = Distance(),
        val district: District? = District(),
        val districts: List<District> = listOf(),
        val fabSort: FabSort = FabSort(),
        val facilities: List<Facility> = listOf(),
        val fenceDiscount: Int = 0,
        val freeCancellation: FreeCancellation = FreeCancellation(),
        val freeCancellationAvailable: Boolean = false,
        val freeCancellationAvailableUntil: String = "",
        val hbid: Int = 0,
        val hostelworldRecommends: Boolean = false,
        val hwExtra: Any? = Any(),
        val id: Int = 0,
        val images: List<Image> = listOf(),
        val imagesGallery: List<ImagesGallery> = listOf(),
        val isElevate: Boolean = false,
        val isFeatured: Boolean = false,
        val isNew: Boolean = false,
        val isPromoted: Boolean = false,
        val latitude: Double = 0.0,
        val longitude: Double = 0.0,
        val lowestAverageDormPricePerNight: LowestAverageDormPricePerNight? = LowestAverageDormPricePerNight(),
        val lowestAveragePricePerNight: LowestAveragePricePerNight = LowestAveragePricePerNight(),
        val lowestAveragePrivatePricePerNight: LowestAveragePrivatePricePerNight = LowestAveragePrivatePricePerNight(),
        val lowestDormPricePerNight: LowestDormPricePerNight? = LowestDormPricePerNight(),
        val lowestPricePerNight: LowestPricePerNight = LowestPricePerNight(),
        val lowestPrivatePricePerNight: LowestPrivatePricePerNight = LowestPrivatePricePerNight(),
        val name: String = "",
        val originalLowestAverageDormPricePerNight: OriginalLowestAverageDormPricePerNight? = OriginalLowestAverageDormPricePerNight(),
        val originalLowestAveragePricePerNight: OriginalLowestAveragePricePerNight = OriginalLowestAveragePricePerNight(),
        val originalLowestAveragePrivatePricePerNight: OriginalLowestAveragePrivatePricePerNight = OriginalLowestAveragePrivatePricePerNight(),
        val overallRating: OverallRating = OverallRating(),
        val overview: String = "",
        val position: Int = 0,
        val promotions: List<Promotion> = listOf(),
        val ratingBreakdown: RatingBreakdown = RatingBreakdown(),
        val starRating: Int = 0,
        val stayRuleViolations: List<StayRuleViolation>? = listOf(),
        val type: String = "",
        val veryPopular: Boolean? = false
    ) {
        data class Distance(
            val units: String = "",
            val value: Double = 0.0
        )

        data class District(
            val id: String = "",
            val name: String = ""
        )

        data class FabSort(
            val rank1: Int = 0,
            val rank2: Int = 0,
            val rank3: Int = 0,
            val rank4: Int = 0,
            val rank5: Int = 0,
            val rank6: Int = 0,
            val rank7: Int = 0,
            val rank8: Int = 0,
            val rank9: Int = 0
        )

        data class Facility(
            val facilities: List<Facility> = listOf(),
            val id: String = "",
            val name: String = ""
        ) {
            data class Facility(
                val id: String = "",
                val name: String = ""
            )
        }

        data class FreeCancellation(
            val description: String = "",
            val label: String = ""
        )

        data class Image(
            val prefix: String = "",
            val suffix: String = ""
        )

        data class ImagesGallery(
            val prefix: String = "",
            val suffix: String = ""
        )

        data class LowestAverageDormPricePerNight(
            val currency: String = "",
            val original: String? = "",
            val promotions: Promotions? = Promotions(),
            val value: String = ""
        ) {
            data class Promotions(
                val promotionsIds: List<Int> = listOf(),
                val totalDiscount: String = ""
            )
        }

        data class LowestAveragePricePerNight(
            val currency: String = "",
            val original: String? = "",
            val promotions: Promotions? = Promotions(),
            val value: String = ""
        ) {
            data class Promotions(
                val promotionsIds: List<Int> = listOf(),
                val totalDiscount: String = ""
            )
        }

        data class LowestAveragePrivatePricePerNight(
            val currency: String = "",
            val original: String? = "",
            val promotions: Promotions? = Promotions(),
            val value: String = ""
        ) {
            data class Promotions(
                val promotionsIds: List<Int> = listOf(),
                val totalDiscount: String = ""
            )
        }

        data class LowestDormPricePerNight(
            val currency: String = "",
            val value: String = ""
        )

        data class LowestPricePerNight(
            val currency: String = "",
            val value: String = ""
        )

        data class LowestPrivatePricePerNight(
            val currency: String = "",
            val value: String = ""
        )

        data class OriginalLowestAverageDormPricePerNight(
            val currency: String = "",
            val value: String = ""
        )

        data class OriginalLowestAveragePricePerNight(
            val currency: String = "",
            val value: String = ""
        )

        data class OriginalLowestAveragePrivatePricePerNight(
            val currency: String = "",
            val value: String = ""
        )

        data class OverallRating(
            val numberOfRatings: String = "",
            val overall: Int = 0
        )

        data class Promotion(
            val discount: Int = 0,
            val id: Int = 0,
            val name: String = "",
            val stack: Boolean = false,
            val type: String = ""
        )

        data class RatingBreakdown(
            val average: Int = 0,
            val clean: Int = 0,
            val facilities: Int = 0,
            val `fun`: Int = 0,
            val location: Int = 0,
            val ratingsCount: Int = 0,
            val security: Int = 0,
            val staff: Int = 0,
            val value: Int = 0
        )

        data class StayRuleViolation(
            val description: String = ""
        )
    }
}