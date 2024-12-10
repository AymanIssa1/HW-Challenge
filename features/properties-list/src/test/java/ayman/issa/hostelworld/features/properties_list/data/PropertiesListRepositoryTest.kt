package ayman.issa.hostelworld.features.properties_list.data

import app.cash.turbine.test
import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.features.properties_list.MainDispatcherRule
import ayman.issa.hostelworld.features.properties_list.data.mappers.toPropertyUiModel
import ayman.issa.hostelworld.features.properties_list.data.repository.PropertiesListRepositoryImpl
import ayman.issa.hostelworld.features.properties_list.domain.repository.PropertiesListRepository
import ayman.issa.hostelworld.features.properties_list.fakeProperty1
import ayman.issa.hostelworld.features.properties_list.fakeProperty2
import ayman.issa.hostelworld.features.properties_list.fakeProperty3
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

class PropertiesListRepositoryTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    @get:Rule(order = 1)
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var sut: PropertiesListRepository

    private val propertiesService: PropertiesService = mockk()
    private val currencyExchangeService: CurrencyExchangeService = mockk()

    private val CURRENCY = "USD"
    private val CURRENCY_RATE = 1.5
    private val currencyExchangeResponse = mockk<CurrenciesResponse>()

    @Before
    fun setUp() {
        sut = PropertiesListRepositoryImpl(propertiesService, currencyExchangeService)
    }

    @Test
    fun callGetProperties_ReturnSuccess() = runTest {
        val propertiesResponse = PropertiesResponse(
            properties = listOf(
                fakeProperty1,
                fakeProperty2,
                fakeProperty3,
            )
        )

        coEvery { currencyExchangeService.getExchangeRates() } returns currencyExchangeResponse
        coEvery { currencyExchangeResponse.rates[CURRENCY] } returns CURRENCY_RATE
        coEvery { propertiesService.getProperties() } returns propertiesResponse

        val result = sut.getProperties(CURRENCY)
        result.test {
//            val firstItem = awaitItem()
//            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            val secondItem = awaitItem()
            Truth.assertThat(secondItem).isEqualTo(
                Response.Success(
                    listOf(
                        fakeProperty1.toPropertyUiModel(CURRENCY, CURRENCY_RATE),
                        fakeProperty2.toPropertyUiModel(CURRENCY, CURRENCY_RATE),
                        fakeProperty3.toPropertyUiModel(CURRENCY, CURRENCY_RATE),
                    )
                )
            )
            awaitComplete()
        }
    }

    @Test
    fun callGetProperties_ReturnException() = runTest {
        val exception = Exception("Error")
        coEvery { currencyExchangeService.getExchangeRates() } throws exception

        val result = sut.getProperties(CURRENCY)
        result.test {
//            val firstItem = awaitItem()
//            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

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
