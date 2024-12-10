package ayman.issa.hostelworld.features.property_details.domain.repository

import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.property_details.domain.model.PropertyDetailsUiModel
import kotlinx.coroutines.flow.Flow


interface PropertyDetailsRepository {
    suspend fun getPropertyDetailsById(propertyId: Int, currency: String): Flow<Response<PropertyDetailsUiModel>>
}