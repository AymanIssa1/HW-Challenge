package ayman.issa.hostelworld.features.properties_list.data.repository

import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.properties_list.data.mappers.toPropertyUiModel
import ayman.issa.hostelworld.remote.api.CurrencyExchangeService
import ayman.issa.hostelworld.remote.api.PropertiesService
import ayman.issa.hostelworld.features.properties_list.domain.repository.PropertiesListRepository
import kotlinx.coroutines.flow.flow

class PropertiesListRepositoryImpl(
    private val propertiesService: PropertiesService,
    private val currencyExchangeService: CurrencyExchangeService
) : PropertiesListRepository {

    override suspend fun getProperties(currency: String) = flow {
        return@flow try {
//            emit(Response.Loading)
            val currencyExchangeResponse = currencyExchangeService.getExchangeRates()
            val currentRate = currencyExchangeResponse.rates[currency]
            val result = propertiesService.getProperties().properties
                .sortedBy { it.isFeatured.not() }
                .map {
                    it.toPropertyUiModel(currency = currency, rate = currentRate ?: 1.0)
                }
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e))
        }
    }

}