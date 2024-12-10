package ayman.issa.hostelworld.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrenciesResponse(
    @Json(name = "base") val base: String,
    @Json(name = "date") val date: String,
    @Json(name = "historical") val historical: Boolean,
    @Json(name = "rates") val rates: Map<String, Double>,
    @Json(name = "success") val success: Boolean,
    @Json(name = "timestamp") val timestamp: Int
)