package ayman.issa.hostelworld.remote.api

import ayman.issa.hostelworld.remote.models.CurrenciesResponse
import retrofit2.http.GET

interface CurrencyExchangeService {

    @GET("16e87e40ca7b9650aa8e1b936f23e14e/raw/15efd901b57c2b66bf0201ee7aa9aa2edc6df779/rates.json")
    suspend fun getExchangeRates(): CurrenciesResponse
}