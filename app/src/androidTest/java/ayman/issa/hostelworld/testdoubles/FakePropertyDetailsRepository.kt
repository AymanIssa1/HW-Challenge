package ayman.issa.hostelworld.testdoubles

import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.fakeProperty1
import ayman.issa.hostelworld.features.property_details.data.mappers.toPropertyDetailUi
import ayman.issa.hostelworld.features.property_details.domain.model.PropertyDetailsUiModel
import ayman.issa.hostelworld.features.property_details.domain.repository.PropertyDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class FakePropertyDetailsRepository : PropertyDetailsRepository {
    override suspend fun getPropertyDetailsById(
        propertyId: Int,
        currency: String
    ): Flow<Response<PropertyDetailsUiModel>> {
        return flowOf(
            Response.Loading,
            Response.Success(fakeProperty1.toPropertyDetailUi("EUR", 1.0))
        )
    }

}