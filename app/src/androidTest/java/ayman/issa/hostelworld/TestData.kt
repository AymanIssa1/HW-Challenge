package ayman.issa.hostelworld

import ayman.issa.hostelworld.remote.models.PropertiesResponse
import ayman.issa.hostelworld.remote.models.PropertiesResponse.Property

val fakeProperty1 = Property(
    name = "The Hostel",
    address1 = "1234 Elm St.",
    address2 = "Apt 101",
    distance = Property.Distance(units = "km", value = 2.5),
    district = Property.District(id = "001", name = "Downtown"),
    districts = listOf(Property.District(id = "002", name = "Uptown")),
    fabSort = Property.FabSort(rank1 = 1, rank2 = 2, rank3 = 3),
    facilities = listOf(Property.Facility(id = "101", name = "Free Wi-Fi")),
    fenceDiscount = 15,
    freeCancellation = Property.FreeCancellation(description = "Free cancellation within 24 hours", label = "Free Cancellation"),
    freeCancellationAvailable = true,
    freeCancellationAvailableUntil = "2024-12-15",
    hbid = 123456,
    hostelworldRecommends = true,
    hwExtra = "Extra feature",
    id = 1,
    images = listOf(Property.Image(prefix = "https://example.com/images/", suffix = "image1.jpg")),
    imagesGallery = listOf(Property.ImagesGallery(prefix = "https://example.com/gallery/", suffix = "gallery1.jpg")),
    isElevate = true,
    isFeatured = true,
    isNew = false,
    isPromoted = false,
    latitude = 40.7128,
    longitude = -74.0060,
    lowestAverageDormPricePerNight = Property.LowestAverageDormPricePerNight(currency = "USD", value = "30.00", promotions = Property.LowestAverageDormPricePerNight.Promotions(promotionsIds = listOf(101), totalDiscount = "10%")),
    lowestAveragePricePerNight = Property.LowestAveragePricePerNight(currency = "USD", value = "60.00", promotions = Property.LowestAveragePricePerNight.Promotions(promotionsIds = listOf(102), totalDiscount = "15%")),
    lowestAveragePrivatePricePerNight = Property.LowestAveragePrivatePricePerNight(currency = "USD", value = "100.00", promotions = Property.LowestAveragePrivatePricePerNight.Promotions(promotionsIds = listOf(103), totalDiscount = "20%")),
    lowestDormPricePerNight = Property.LowestDormPricePerNight(currency = "USD", value = "25.00"),
    lowestPricePerNight = Property.LowestPricePerNight(currency = "USD", value = "50.00"),
    lowestPrivatePricePerNight = Property.LowestPrivatePricePerNight(currency = "USD", value = "80.00"),
    originalLowestAverageDormPricePerNight = Property.OriginalLowestAverageDormPricePerNight(currency = "USD", value = "35.00"),
    originalLowestAveragePricePerNight = Property.OriginalLowestAveragePricePerNight(currency = "USD", value = "70.00"),
    originalLowestAveragePrivatePricePerNight = Property.OriginalLowestAveragePrivatePricePerNight(currency = "USD", value = "110.00"),
    overallRating = Property.OverallRating(numberOfRatings = "120", overall = 4),
    overview = "A cozy and affordable hostel in the heart of the city.",
    position = 1,
    promotions = listOf(Property.Promotion(discount = 10, id = 1, name = "Early Bird", stack = true, type = "Discount")),
    ratingBreakdown = Property.RatingBreakdown(average = 4, clean = 4, facilities = 3, `fun` = 5, location = 4, ratingsCount = 120, security = 4, staff = 5, value = 4),
    starRating = 4,
    stayRuleViolations = listOf(Property.StayRuleViolation(description = "No pets allowed")),
    type = "Hostel",
    veryPopular = true
)

val fakeProperty2 = Property(
    address1 = "5678 Maple Ave.",
    address2 = "Suite 202",
    distance = Property.Distance(units = "mi", value = 1.0),
    district = Property.District(id = "003", name = "Midtown"),
    districts = listOf(Property.District(id = "004", name = "Suburbia")),
    fabSort = Property.FabSort(rank1 = 3, rank2 = 2, rank3 = 1),
    facilities = listOf(Property.Facility(id = "102", name = "Swimming Pool")),
    fenceDiscount = 20,
    freeCancellation = Property.FreeCancellation(description = "Free cancellation within 48 hours", label = "Free Cancellation"),
    freeCancellationAvailable = true,
    freeCancellationAvailableUntil = "2024-12-20",
    hbid = 654321,
    hostelworldRecommends = false,
    hwExtra = "Additional feature",
    id = 2,
    images = listOf(Property.Image(prefix = "https://example.com/images/", suffix = "image2.jpg")),
    imagesGallery = listOf(Property.ImagesGallery(prefix = "https://example.com/gallery/", suffix = "gallery2.jpg")),
    isElevate = false,
    isFeatured = true,
    isNew = true,
    isPromoted = true,
    latitude = 34.0522,
    longitude = -118.2437,
    lowestAverageDormPricePerNight = Property.LowestAverageDormPricePerNight(currency = "USD", value = "40.00", promotions = Property.LowestAverageDormPricePerNight.Promotions(promotionsIds = listOf(201), totalDiscount = "12%")),
    lowestAveragePricePerNight = Property.LowestAveragePricePerNight(currency = "USD", value = "75.00", promotions = Property.LowestAveragePricePerNight.Promotions(promotionsIds = listOf(202), totalDiscount = "18%")),
    lowestAveragePrivatePricePerNight = Property.LowestAveragePrivatePricePerNight(currency = "USD", value = "120.00", promotions = Property.LowestAveragePrivatePricePerNight.Promotions(promotionsIds = listOf(203), totalDiscount = "25%")),
    lowestDormPricePerNight = Property.LowestDormPricePerNight(currency = "USD", value = "30.00"),
    lowestPricePerNight = Property.LowestPricePerNight(currency = "USD", value = "55.00"),
    lowestPrivatePricePerNight = Property.LowestPrivatePricePerNight(currency = "USD", value = "85.00"),
    name = "Maple Avenue Hotel",
    originalLowestAverageDormPricePerNight = Property.OriginalLowestAverageDormPricePerNight(currency = "USD", value = "45.00"),
    originalLowestAveragePricePerNight = Property.OriginalLowestAveragePricePerNight(currency = "USD", value = "80.00"),
    originalLowestAveragePrivatePricePerNight = Property.OriginalLowestAveragePrivatePricePerNight(currency = "USD", value = "130.00"),
    overallRating = Property.OverallRating(numberOfRatings = "200", overall = 5),
    overview = "A modern hotel offering luxurious amenities and prime location.",
    position = 2,
    promotions = listOf(Property.Promotion(discount = 15, id = 2, name = "Holiday Special", stack = false, type = "Discount")),
    ratingBreakdown = Property.RatingBreakdown(average = 5, clean = 5, facilities = 5, `fun` = 4, location = 5, ratingsCount = 200, security = 5, staff = 5, value = 4),
    starRating = 5,
    stayRuleViolations = listOf(Property.StayRuleViolation(description = "No smoking in rooms")),
    type = "Hotel",
    veryPopular = false
)

val fakeProperty3 = Property(
    address1 = "9101 Oak Blvd.",
    address2 = "Unit 303",
    distance = Property.Distance(units = "km", value = 3.0),
    district = Property.District(id = "005", name = "East Side"),
    districts = listOf(Property.District(id = "006", name = "West Side")),
    fabSort = Property.FabSort(rank1 = 4, rank2 = 1, rank3 = 2),
    facilities = listOf(Property.Facility(id = "103", name = "Fitness Center")),
    fenceDiscount = 10,
    freeCancellation = Property.FreeCancellation(description = "Free cancellation until check-in", label = "Free Cancellation"),
    freeCancellationAvailable = true,
    freeCancellationAvailableUntil = "2024-12-10",
    hbid = 789012,
    hostelworldRecommends = true,
    hwExtra = "Special offer",
    id = 3,
    images = listOf(Property.Image(prefix = "https://example.com/images/", suffix = "image3.jpg")),
    imagesGallery = listOf(Property.ImagesGallery(prefix = "https://example.com/gallery/", suffix = "gallery3.jpg")),
    isElevate = false,
    isFeatured = false,
    isNew = true,
    isPromoted = false,
    latitude = 51.5074,
    longitude = -0.1278,
    lowestAverageDormPricePerNight = Property.LowestAverageDormPricePerNight(currency = "USD", value = "25.00", promotions = Property.LowestAverageDormPricePerNight.Promotions(promotionsIds = listOf(301), totalDiscount = "8%")),
    lowestAveragePricePerNight = Property.LowestAveragePricePerNight(currency = "USD", value = "55.00", promotions = Property.LowestAveragePricePerNight.Promotions(promotionsIds = listOf(302), totalDiscount = "10%")),
    lowestAveragePrivatePricePerNight = Property.LowestAveragePrivatePricePerNight(currency = "USD", value = "90.00", promotions = Property.LowestAveragePrivatePricePerNight.Promotions(promotionsIds = listOf(303), totalDiscount = "15%")),
    lowestDormPricePerNight = Property.LowestDormPricePerNight(currency = "USD", value = "20.00"),
    lowestPricePerNight = Property.LowestPricePerNight(currency = "USD", value = "45.00"),
    lowestPrivatePricePerNight = Property.LowestPrivatePricePerNight(currency = "USD", value = "75.00"),
    name = "Oak Boulevard Inn",
    originalLowestAverageDormPricePerNight = Property.OriginalLowestAverageDormPricePerNight(currency = "USD", value = "30.00"),
    originalLowestAveragePricePerNight = Property.OriginalLowestAveragePricePerNight(currency = "USD", value = "65.00"),
    originalLowestAveragePrivatePricePerNight = Property.OriginalLowestAveragePrivatePricePerNight(currency = "USD", value = "100.00"),
    overallRating = Property.OverallRating(numberOfRatings = "150", overall = 4),
    overview = "Affordable accommodation with great facilities.",
    position = 3,
    promotions = listOf(Property.Promotion(discount = 5, id = 3, name = "Weekend Special", stack = true, type = "Discount")),
    ratingBreakdown = Property.RatingBreakdown(average = 4, clean = 4, facilities = 4, `fun` = 3, location = 4, ratingsCount = 150, security = 4, staff = 4, value = 3),
    starRating = 4,
    stayRuleViolations = listOf(Property.StayRuleViolation(description = "No loud noises after 10 PM")),
    type = "Inn",
    veryPopular = true
)

val propertiesList = listOf(fakeProperty1, fakeProperty2, fakeProperty3)
val propertiesResponse = PropertiesResponse(properties = propertiesList)