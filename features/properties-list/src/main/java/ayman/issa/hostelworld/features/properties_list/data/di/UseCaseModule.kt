package ayman.issa.hostelworld.features.properties_list.data.di

import ayman.issa.hostelworld.common.di.DefaultDispatcher
import ayman.issa.hostelworld.features.properties_list.domain.repository.PropertiesListRepository
import ayman.issa.hostelworld.features.properties_list.domain.usecase.GetPropertiesListUseCase
import ayman.issa.hostelworld.features.properties_list.domain.usecase.PropertiesListUseCases
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
    fun providePropertiesListUseCases(
        propertiesListRepository: PropertiesListRepository,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): PropertiesListUseCases {
        return PropertiesListUseCases(
            getPropertiesListUseCase = GetPropertiesListUseCase(
                propertiesListRepository = propertiesListRepository,
                defaultDispatcher = defaultDispatcher
            )
        )
    }
}


