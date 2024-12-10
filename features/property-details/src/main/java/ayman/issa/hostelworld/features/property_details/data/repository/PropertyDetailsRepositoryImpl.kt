package ayman.issa.hostelworld.features.property_details.data.repository

import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.property_details.data.mappers.toPropertyDetailUi
import ayman.issa.hostelworld.features.property_details.domain.repository.PropertyDetailsRepository
import ayman.issa.hostelworld.remote.api.CurrencyExchangeService
import ayman.issa.hostelworld.remote.api.PropertiesService
import kotlinx.coroutines.flow.flow

class PropertyDetailsRepositoryImpl(
    private val propertiesService: PropertiesService,
    private val currencyExchangeService: CurrencyExchangeService
) : PropertyDetailsRepository {
    override suspend fun getPropertyDetailsById(propertyId: Int, currency: String) = flow {
        try {
            emit(Response.Loading)
            val currencyExchangeResponse = currencyExchangeService.getExchangeRates()
            val currentRate = currencyExchangeResponse.rates[currency]
            val result = propertiesService.getProperties().properties
                .find { it.id == propertyId }
                ?.toPropertyDetailUi(currency, currentRate ?: 1.0)
            result?.let { emit(Response.Success(it)) }
        } catch (e: Exception) {
            emit(Response.Error(e))
        }

    }

}