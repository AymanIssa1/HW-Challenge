package ayman.issa.hostelworld.property_details.data.presentation

import app.cash.turbine.test
import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.property_details.data.mappers.toPropertyDetailUi
import ayman.issa.hostelworld.features.property_details.data.repository.PropertyDetailsRepositoryImpl
import ayman.issa.hostelworld.features.property_details.domain.repository.PropertyDetailsRepository
import ayman.issa.hostelworld.property_details.MainDispatcherRule
import ayman.issa.hostelworld.property_details.fakeProperty
import ayman.issa.hostelworld.remote.api.CurrencyExchangeService
import ayman.issa.hostelworld.remote.api.PropertiesService
import ayman.issa.hostelworld.remote.models.CurrenciesResponse
import ayman.issa.hostelworld.remote.models.PropertiesResponse
import com.google.common.truth.Truth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PropertyDetailsRepositoryTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    @get:Rule(order = 1)
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var sut: PropertyDetailsRepository

    private val propertiesService: PropertiesService = mockk()
    private val currencyExchangeService: CurrencyExchangeService = mockk()

    private val PROPERTY_ID = 123
    private val CURRENCY = "USD"
    private val CURRENCY_RATE = 1.5
    private val propertyDetailsUiModel = fakeProperty.toPropertyDetailUi(CURRENCY, CURRENCY_RATE)
    private val currencyExchangeResponse = mockk<CurrenciesResponse>()

    @Before
    fun setUp() {
        sut = PropertyDetailsRepositoryImpl(propertiesService, currencyExchangeService)
    }

    @Test
    fun callGetPropertyDetailsById_ReturnSuccess() = runTest {
        val propertiesResponse = PropertiesResponse(
            properties = listOf(
                fakeProperty
            )
        )

        coEvery { currencyExchangeService.getExchangeRates() } returns currencyExchangeResponse
        coEvery { currencyExchangeResponse.rates[CURRENCY] } returns CURRENCY_RATE
        coEvery { propertiesService.getProperties() } returns propertiesResponse

        val result = sut.getPropertyDetailsById(PROPERTY_ID, CURRENCY)
        result.test {
            val firstItem = awaitItem()
            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            val secondItem = awaitItem()
            Truth.assertThat(secondItem).isEqualTo(Response.Success(propertyDetailsUiModel))
            awaitComplete()
        }
    }

    @Test
    fun callGetPropertyDetailsById_ReturnException() = runTest {
        val exception = Exception("Error")
        coEvery { currencyExchangeService.getExchangeRates() } throws exception

        val result = sut.getPropertyDetailsById(PROPERTY_ID, CURRENCY)
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
