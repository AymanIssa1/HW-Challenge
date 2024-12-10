package ayman.issa.hostelworld.property_details

import ayman.issa.hostelworld.remote.models.PropertiesResponse

val fakeProperty = PropertiesResponse.Property(
    id = 123,
    address1 = "123 Main Street",
    address2 = "Apartment 4B",
    distance = PropertiesResponse.Property.Distance(
        units = "km",
        value = 1.5
    ),
    district = PropertiesResponse.Property.District(
        id = "1",
        name = "Downtown"
    ),
    districts = listOf(
        PropertiesResponse.Property.District(
            id = "1",
            name = "Downtown"
        ),
        PropertiesResponse.Property.District(
            id = "2",
            name = "Old Town"
        )
    ),
    fabSort = PropertiesResponse.Property.FabSort(
        rank1 = 1, rank2 = 2, rank3 = 3, rank4 = 4,
        rank5 = 5, rank6 = 6, rank7 = 7, rank8 = 8, rank9 = 9
    ),
    facilities = listOf(
        PropertiesResponse.Property.Facility(
            id = "101",
            name = "Free Wi-Fi"
        ),
        PropertiesResponse.Property.Facility(
            id = "102",
            name = "Parking"
        )
    ),
    fenceDiscount = 10,
    freeCancellation = PropertiesResponse.Property.FreeCancellation(
        description = "Cancel up to 24 hours before check-in",
        label = "Free Cancellation"
    ),
    freeCancellationAvailable = true,
    freeCancellationAvailableUntil = "2024-12-31",
    hbid = 12345,
    hostelworldRecommends = true,
    hwExtra = null,
    images = listOf(
        PropertiesResponse.Property.Image(
            prefix = "https://example.com/",
            suffix = "image1.jpg"
        ),
        PropertiesResponse.Property.Image(
            prefix = "https://example.com/",
            suffix = "image2.jpg"
        )
    ),
    imagesGallery = listOf(
        PropertiesResponse.Property.ImagesGallery(
            prefix = "https://example.com/gallery/",
            suffix = "img1.jpg"
        ),
        PropertiesResponse.Property.ImagesGallery(
            prefix = "https://example.com/gallery/",
            suffix = "img2.jpg"
        )
    ),
    isElevate = false,
    isFeatured = true,
    isNew = false,
    isPromoted = true,
    latitude = 40.7128,
    longitude = -74.0060,
    lowestAverageDormPricePerNight = PropertiesResponse.Property.LowestAverageDormPricePerNight(
        currency = "USD",
        original = "25",
        promotions = PropertiesResponse.Property.LowestAverageDormPricePerNight.Promotions(
            promotionsIds = listOf(1, 2),
            totalDiscount = "5"
        ),
        value = "20"
    ),
    lowestAveragePricePerNight = PropertiesResponse.Property.LowestAveragePricePerNight(
        currency = "USD",
        original = "30",
        promotions = null,
        value = "28"
    ),
    lowestAveragePrivatePricePerNight = PropertiesResponse.Property.LowestAveragePrivatePricePerNight(
        currency = "USD",
        original = "40",
        promotions = null,
        value = "38"
    ),
    lowestDormPricePerNight = PropertiesResponse.Property.LowestDormPricePerNight(
        currency = "USD",
        value = "15"
    ),
    lowestPricePerNight = PropertiesResponse.Property.LowestPricePerNight(
        currency = "USD",
        value = "12"
    ),
    lowestPrivatePricePerNight = PropertiesResponse.Property.LowestPrivatePricePerNight(
        currency = "USD",
        value = "35"
    ),
    name = "Cozy Hostel",
    originalLowestAverageDormPricePerNight = PropertiesResponse.Property.OriginalLowestAverageDormPricePerNight(
        currency = "USD",
        value = "25"
    ),
    originalLowestAveragePricePerNight = PropertiesResponse.Property.OriginalLowestAveragePricePerNight(
        currency = "USD",
        value = "30"
    ),
    originalLowestAveragePrivatePricePerNight = PropertiesResponse.Property.OriginalLowestAveragePrivatePricePerNight(
        currency = "USD",
        value = "40"
    ),
    overallRating = PropertiesResponse.Property.OverallRating(
        numberOfRatings = "250",
        overall = 4
    ),
    overview = "Cozy and affordable hostel in the heart of the city.",
    position = 1,
    promotions = listOf(
        PropertiesResponse.Property.Promotion(
            discount = 10,
            id = 1,
            name = "Holiday Discount",
            stack = true,
            type = "Seasonal"
        )
    ),
    ratingBreakdown = PropertiesResponse.Property.RatingBreakdown(
        average = 4,
        clean = 5,
        facilities = 4,
        `fun` = 4,
        location = 5,
        ratingsCount = 250,
        security = 5,
        staff = 4,
        value = 4
    ),
    starRating = 3,
    stayRuleViolations = listOf(
        PropertiesResponse.Property.StayRuleViolation(description = "No pets allowed.")
    ),
    type = "Hostel",
    veryPopular = true
)
