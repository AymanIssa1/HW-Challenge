package ayman.issa.hostelworld.property_details.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.common.util.Constants
import ayman.issa.hostelworld.features.property_details.data.mappers.toPropertyDetailUi
import ayman.issa.hostelworld.features.property_details.domain.usecase.PropertyDetailsUseCases
import ayman.issa.hostelworld.features.property_details.presentation.PropertyDetailsEvents
import ayman.issa.hostelworld.features.property_details.presentation.PropertyDetailsViewModel
import ayman.issa.hostelworld.property_details.MainDispatcherRule
import ayman.issa.hostelworld.property_details.fakeProperty
import com.google.common.truth.Truth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PropertyDetailsViewModelTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    @get:Rule(order = 1)
    val mainDispatcherRule = MainDispatcherRule(StandardTestDispatcher())

    private lateinit var sut: PropertyDetailsViewModel

    private var propertyDetailsUseCases: PropertyDetailsUseCases = mockk()

    private val savedStateHandle: SavedStateHandle = SavedStateHandle()

    private val propertyDetail = fakeProperty

    private val PROPERTY_ID = 123
    private val CURRENCY = "USD"
    private val EXCHAGE_RATE = 1.5

    @Before
    fun setUp() {
        savedStateHandle[Constants.PROPERTY_ID] = PROPERTY_ID
        savedStateHandle[Constants.CURRENCY_TYPE] = CURRENCY

        coEvery {
            propertyDetailsUseCases.getPropertyDetailsUseCase(PROPERTY_ID, CURRENCY)
        } returns flow {
            emit(Response.Loading)
            emit(Response.Success(propertyDetail.toPropertyDetailUi(CURRENCY, EXCHAGE_RATE)))
        }

        sut = PropertyDetailsViewModel(propertyDetailsUseCases, savedStateHandle)
    }

    @Test
    fun `test initial state is Loading`() = runTest {
        sut.state.test {
            val item = awaitItem()
            Truth.assertThat(item).isEqualTo(Response.Loading)
        }
    }

    @Test
    fun `test calling onEvent PropertyDetailsEvents - state is Success`() = runTest {
        coEvery {
            propertyDetailsUseCases.getPropertyDetailsUseCase(PROPERTY_ID, CURRENCY)
        } returns flow {
            emit(Response.Loading)
            emit(Response.Success(propertyDetail.toPropertyDetailUi(CURRENCY, EXCHAGE_RATE)))
        }

        sut.onEvent(PropertyDetailsEvents.GetPropertyDetails(PROPERTY_ID, CURRENCY))

        sut.state.test {
            val firstItem = awaitItem()
            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            Truth.assertThat(awaitItem()).isEqualTo(Response.Success(propertyDetail.toPropertyDetailUi(CURRENCY, EXCHAGE_RATE)))
        }
    }

    @Test
    fun `test calling onEvent PropertyDetailsEvents - state is Failed`() = runTest {
        val exception = Exception("Error")

        propertyDetailsUseCases = mockk()
        sut = PropertyDetailsViewModel(propertyDetailsUseCases, SavedStateHandle())

        coEvery {
            propertyDetailsUseCases.getPropertyDetailsUseCase(any(), any())
        } returns flow {
            emit(Response.Loading)
            emit(Response.Error(exception))
        }

        sut.onEvent(PropertyDetailsEvents.GetPropertyDetails(-1, CURRENCY))

        sut.state.test {
            val firstItem = awaitItem()
            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            val secondItem = awaitItem()
            Truth.assertThat(secondItem).isEqualTo(Response.Error(exception))
        }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}
