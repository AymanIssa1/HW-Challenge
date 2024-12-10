package ayman.issa.hostelworld.features.properties_list.domain

import app.cash.turbine.test
import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.properties_list.data.mappers.toPropertyUiModel
import ayman.issa.hostelworld.features.properties_list.domain.repository.PropertiesListRepository
import ayman.issa.hostelworld.features.properties_list.domain.usecase.GetPropertiesListUseCase
import ayman.issa.hostelworld.features.properties_list.fakeProperty1
import ayman.issa.hostelworld.features.properties_list.fakeProperty2
import ayman.issa.hostelworld.features.properties_list.fakeProperty3
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
class GetPropertiesListUseCaseTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    private lateinit var sut: GetPropertiesListUseCase

    private var mockPropertiesListRepository: PropertiesListRepository = mockk()

    val CURRENCY = "USD"
    val RATE = 1.5
    val propertiesListUiModel = listOf(
        fakeProperty1.toPropertyUiModel(CURRENCY, RATE),
        fakeProperty2.toPropertyUiModel(CURRENCY, RATE),
        fakeProperty3.toPropertyUiModel(CURRENCY, RATE),
    )

    @Before
    fun setUp() {
        sut = GetPropertiesListUseCase(mockPropertiesListRepository, UnconfinedTestDispatcher())
    }

    @Test
    fun callGetPropertiesListUseCase_ReturnSuccess() = runTest {
        coEvery {
            mockPropertiesListRepository.getProperties(CURRENCY)
        } returns flowOf(Response.Loading, Response.Success(propertiesListUiModel))

        val result = sut(CURRENCY)
        result.test {
            val firstItem = awaitItem()
            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            val secondItem = awaitItem()
            Truth.assertThat(secondItem).isEqualTo(Response.Success(propertiesListUiModel))
            awaitComplete()
        }
    }

    @Test
    fun callGetPropertiesListUseCase_ReturnException() = runTest {
        val exception = Exception("Error")
        coEvery {
            mockPropertiesListRepository.getProperties(CURRENCY)
        } returns flowOf(Response.Loading, Response.Error(exception))

        val result = sut(CURRENCY)
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
