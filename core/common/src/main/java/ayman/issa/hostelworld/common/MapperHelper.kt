package ayman.issa.hostelworld.common

/**
 * Returns a description for a given rating.
 *
 * @param rating The rating as a Float (0.0 to 10.0).
 * @return A descriptive string corresponding to the rating range:
 *   - "Superb" for ratings between 9.0 and 10.0
 *   - "Fabulous" for ratings between 8.0 and 9.0
 *   - "Very Good" for ratings between 7.0 and 8.0
 *   - "Good" for ratings between 6.0 and 7.0
 *   - An empty string for any other rating values.
 */
fun getRatingDescription(rating: Float): String {
    return when {
        rating in 9.0..10.0 -> "Superb"
        rating in 8.0..9.0 -> "Fabulous"
        rating in 7.0..8.0 -> "Very Good"
        rating in 6.0..7.0 -> "Good"
        else -> ""
    }
}

/**
 * Calculates the discount percentage based on the original and discounted prices.
 *
 * @param discountedPrice The discounted price as a Float.
 * @param originalPrice The original price as a Float.
 * @return The discount percentage as an integer. The value represents how much
 *   of the original price has been discounted.
 *   - For example, if the discounted price is 75.0 and the original price is 100.0,
 *     the function returns 25 (indicating a 25% discount).
 * @throws ArithmeticException If the original price is zero.
 */
fun getDiscount(discountedPrice: Double, originalPrice: Double): Int {
    require(originalPrice > 0) { "Original price must be greater than 0" }
    return (((originalPrice - discountedPrice) / originalPrice) * 100).toInt()
}

/**
 * Retrieves the currency sign for a given currency code.
 *
 * @param currencyCode as a String (e.g., "USD", "EUR").
 * @return The corresponding currency sign as a String. Returns:
 *   - "€" for "EUR" (Euro)
 *   - "$" for "USD" (US Dollar)
 *   - "£" for "GBP" (British Pound)
 *   - The original currency code (unchanged) if no matching sign is found.
 */
fun getCurrencySign(currencyCode: String): String {
    return when (currencyCode.uppercase()) {
        "EUR" -> "€"  // Euro
        "USD" -> "$"  // US Dollar
        "GBP" -> "£"  // British Pound
        else -> currencyCode
    }
}

/**
 * Converts a price using a given rate.
 *
 * @param price The original price to convert.
 * @param rate The conversion rate to apply.
 * @return The converted price.
 */
fun convertPrice(price: Double, rate: Double): Double {
    require(rate > 0) { "Rate must be greater than 0" }
    return String.format("%.2f", price * rate).toDouble()
}