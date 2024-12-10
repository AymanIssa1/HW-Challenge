package ayman.issa.hostelworld.features.property_details.data.di

import ayman.issa.hostelworld.features.property_details.data.repository.PropertyDetailsRepositoryImpl
import ayman.issa.hostelworld.features.property_details.domain.repository.PropertyDetailsRepository
import ayman.issa.hostelworld.remote.api.CurrencyExchangeService
import ayman.issa.hostelworld.remote.api.PropertiesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePropertyDetailsRepository(
        propertiesService: PropertiesService,
        currencyExchangeService: CurrencyExchangeService
    ): PropertyDetailsRepository {
        return PropertyDetailsRepositoryImpl(
            propertiesService = propertiesService,
            currencyExchangeService = currencyExchangeService
        )
    }

}