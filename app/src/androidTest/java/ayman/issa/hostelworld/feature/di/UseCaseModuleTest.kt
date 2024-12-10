package ayman.issa.hostelworld.feature.di

import ayman.issa.hostelworld.features.properties_list.domain.usecase.GetPropertiesListUseCase
import ayman.issa.hostelworld.features.properties_list.domain.usecase.PropertiesListUseCases
import ayman.issa.hostelworld.features.property_details.domain.usecase.GetPropertyDetailsUseCase
import ayman.issa.hostelworld.features.property_details.domain.usecase.PropertyDetailsUseCases
import ayman.issa.hostelworld.testdoubles.FakePropertiesListRepository
import ayman.issa.hostelworld.testdoubles.FakePropertyDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModuleTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    @Singleton
    fun providePropertiesListUseCases(): PropertiesListUseCases {
        return PropertiesListUseCases(
            getPropertiesListUseCase = GetPropertiesListUseCase(
                propertiesListRepository = FakePropertiesListRepository(),
                defaultDispatcher = UnconfinedTestDispatcher()
            )
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    @Singleton
    fun providePropertyDetailsUseCases(): PropertyDetailsUseCases {
        return PropertyDetailsUseCases(
            getPropertyDetailsUseCase = GetPropertyDetailsUseCase(
                propertyDetailsRepository = FakePropertyDetailsRepository(),
                defaultDispatcher = UnconfinedTestDispatcher()
            )
        )
    }
}


