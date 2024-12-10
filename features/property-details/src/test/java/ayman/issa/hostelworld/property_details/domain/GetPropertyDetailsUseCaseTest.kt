package ayman.issa.hostelworld.property_details.domain

import app.cash.turbine.test
import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.property_details.data.mappers.toPropertyDetailUi
import ayman.issa.hostelworld.features.property_details.domain.repository.PropertyDetailsRepository
import ayman.issa.hostelworld.features.property_details.domain.usecase.GetPropertyDetailsUseCase
import ayman.issa.hostelworld.property_details.fakeProperty
import com.google.common.truth.Truth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetPropertyDetailsUseCaseTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    private lateinit var sut: GetPropertyDetailsUseCase

    private var mockPropertyDetailsRepository: PropertyDetailsRepository = mockk()

    val PROPERTY_ID = 123
    val CURRENCY = "USD"
    val CURRENCY_RATE = 1.5
    val propertyDetailsUiModel = fakeProperty.toPropertyDetailUi(CURRENCY, CURRENCY_RATE)

    @Before
    fun setUp() {
        sut = GetPropertyDetailsUseCase(mockPropertyDetailsRepository, UnconfinedTestDispatcher())
    }

    @Test
    fun callGetPropertyDetailsUseCase_ReturnSuccess() = runTest {
        coEvery {
            mockPropertyDetailsRepository.getPropertyDetailsById(PROPERTY_ID, CURRENCY)
        } returns flowOf(Response.Loading, Response.Success(propertyDetailsUiModel))

        val result = sut(PROPERTY_ID, CURRENCY)
        result.test {
            val firstItem = awaitItem()
            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            val secondItem = awaitItem()
            Truth.assertThat(secondItem).isEqualTo(Response.Success(propertyDetailsUiModel))
            awaitComplete()
        }
    }

    @Test
    fun callGetPropertyDetailsUseCase_ReturnException() = runTest {
        val exception = Exception("Error")
        coEvery {
            mockPropertyDetailsRepository.getPropertyDetailsById(PROPERTY_ID, CURRENCY)
        } returns flowOf(Response.Loading, Response.Error(exception))

        val result = sut(PROPERTY_ID, CURRENCY)
        result.test {
            val firstItem = awaitItem()
            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            val secondItem = awaitItem()
            Truth.assertThat(secondItem).isEqualTo(Response.Error(exception))
            awaitComplete()
        }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}
