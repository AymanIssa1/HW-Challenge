package ayman.issa.hostelworld.features.properties_list.data.di

import ayman.issa.hostelworld.remote.api.CurrencyExchangeService
import ayman.issa.hostelworld.remote.api.PropertiesService
import ayman.issa.hostelworld.features.properties_list.data.repository.PropertiesListRepositoryImpl
import ayman.issa.hostelworld.features.properties_list.domain.repository.PropertiesListRepository
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
    fun providePropertiesListRepository(
        propertiesService: PropertiesService,
        currencyExchangeService: CurrencyExchangeService
    ): PropertiesListRepository {
        return PropertiesListRepositoryImpl(
            propertiesService = propertiesService,
            currencyExchangeService = currencyExchangeService
        )
    }

}