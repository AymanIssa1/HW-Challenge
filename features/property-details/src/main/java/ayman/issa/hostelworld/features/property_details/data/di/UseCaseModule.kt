package ayman.issa.hostelworld.features.property_details.data.di

import ayman.issa.hostelworld.common.di.DefaultDispatcher
import ayman.issa.hostelworld.features.property_details.domain.repository.PropertyDetailsRepository
import ayman.issa.hostelworld.features.property_details.domain.usecase.GetPropertyDetailsUseCase
import ayman.issa.hostelworld.features.property_details.domain.usecase.PropertyDetailsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providePropertyDetailsUseCases(
        propertyDetailsRepository: PropertyDetailsRepository,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): PropertyDetailsUseCases {
        return PropertyDetailsUseCases(
            getPropertyDetailsUseCase = GetPropertyDetailsUseCase(
                propertyDetailsRepository = propertyDetailsRepository,
                defaultDispatcher = defaultDispatcher
            )
        )
    }
}


