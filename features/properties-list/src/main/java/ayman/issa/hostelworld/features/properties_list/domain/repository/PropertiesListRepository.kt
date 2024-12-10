package ayman.issa.hostelworld.features.properties_list.domain.repository

import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.properties_list.domain.model.PropertyUiModel
import kotlinx.coroutines.flow.Flow


interface PropertiesListRepository {
    suspend fun getProperties(currency: String): Flow<Response<List<PropertyUiModel>>>
}