package ayman.issa.hostelworld.features.properties_list.presentation

import ayman.issa.hostelworld.features.properties_list.MainDispatcherRule
import ayman.issa.hostelworld.features.properties_list.data.mappers.toPropertyUiModel
import ayman.issa.hostelworld.features.properties_list.fakeProperty1
import ayman.issa.hostelworld.features.properties_list.fakeProperty2
import ayman.issa.hostelworld.features.properties_list.fakeProperty3
import app.cash.turbine.test
import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.properties_list.domain.usecase.PropertiesListUseCases
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

class PropertiesListViewModelTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    @get:Rule(order = 1)
    val mainDispatcherRule = MainDispatcherRule(StandardTestDispatcher())

    private lateinit var sut: PropertiesListViewModel

    private var propertiesListUseCases: PropertiesListUseCases = mockk()

    private val CURRENCY = "USD"
    private val EXCHANGE_RATE = 1.5
    private val propertiesListUiModel = listOf(
        fakeProperty1.toPropertyUiModel(CURRENCY, EXCHANGE_RATE),
        fakeProperty2.toPropertyUiModel(CURRENCY, EXCHANGE_RATE),
        fakeProperty3.toPropertyUiModel(CURRENCY, EXCHANGE_RATE),
    )

    @Before
    fun setUp() {
        coEvery {
            propertiesListUseCases.getPropertiesListUseCase(any())
        } returns flow {
            emit(Response.Loading)
            emit(Response.Success(propertiesListUiModel))
        }

        sut = PropertiesListViewModel(propertiesListUseCases)
    }

    @Test
    fun `test initial state is Loading`() = runTest {
        sut.state.test {
            val item = awaitItem()
            Truth.assertThat(item).isEqualTo(Response.Loading)
        }
    }

    @Test
    fun `test calling onEvent PropertiesListEvents - state is Success`() = runTest {
        coEvery {
            propertiesListUseCases.getPropertiesListUseCase(CURRENCY)
        } returns flow {
            emit(Response.Loading)
            emit(Response.Success(propertiesListUiModel))
        }

        sut.onEvent(PropertiesListEvents.GetPropertiesList(CURRENCY))

        sut.state.test {
            val firstItem = awaitItem()
            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            Truth.assertThat(awaitItem()).isEqualTo(Response.Success(propertiesListUiModel))
        }
    }

    @Test
    fun `test calling onEvent PropertiesListEvents - state is Failed`() = runTest {
        val exception = Exception("Error")

        propertiesListUseCases = mockk()
        sut = PropertiesListViewModel(propertiesListUseCases)

        coEvery {
            propertiesListUseCases.getPropertiesListUseCase(any())
        } returns flow {
            emit(Response.Loading)
            emit(Response.Error(exception))
        }

        sut.onEvent(PropertiesListEvents.GetPropertiesList("EUR"))

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
