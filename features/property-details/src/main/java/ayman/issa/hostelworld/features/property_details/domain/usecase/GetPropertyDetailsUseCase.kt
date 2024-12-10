package ayman.issa.hostelworld.features.property_details.domain.usecase

import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.property_details.domain.model.PropertyDetailsUiModel
import ayman.issa.hostelworld.features.property_details.domain.repository.PropertyDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetPropertyDetailsUseCase(
    private val propertyDetailsRepository: PropertyDetailsRepository,
    private val defaultDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(propertyId: Int, currency: String): Flow<Response<PropertyDetailsUiModel>> {
        return propertyDetailsRepository
            .getPropertyDetailsById(propertyId, currency)
            .flowOn(defaultDispatcher)
    }
}