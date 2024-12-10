package ayman.issa.hostelworld.features.properties_list.domain.usecase

import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.properties_list.domain.model.PropertyUiModel
import ayman.issa.hostelworld.features.properties_list.domain.repository.PropertiesListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetPropertiesListUseCase(
    private val propertiesListRepository: PropertiesListRepository,
    private val defaultDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(currency: String): Flow<Response<List<PropertyUiModel>>> {
        return propertiesListRepository.getProperties(currency).flowOn(defaultDispatcher)
    }
}