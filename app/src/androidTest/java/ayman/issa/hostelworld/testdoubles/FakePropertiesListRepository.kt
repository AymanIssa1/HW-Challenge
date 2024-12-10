package ayman.issa.hostelworld.testdoubles

import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.properties_list.data.mappers.toPropertyUiModel
import ayman.issa.hostelworld.features.properties_list.domain.model.PropertyUiModel
import ayman.issa.hostelworld.features.properties_list.domain.repository.PropertiesListRepository
import ayman.issa.hostelworld.propertiesList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakePropertiesListRepository : PropertiesListRepository {
    override suspend fun getProperties(currency: String): Flow<Response<List<PropertyUiModel>>> {
        val propertyListUIModels = propertiesList.map {
            it.toPropertyUiModel("EUR", 1.0)
        }
        return flowOf(
            Response.Loading,
            Response.Success(propertyListUIModels)
        )
    }
}