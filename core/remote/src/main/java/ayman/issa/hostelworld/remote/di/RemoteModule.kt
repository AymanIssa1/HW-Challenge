package ayman.issa.hostelworld.remote.di

import ayman.issa.hostelworld.common.util.Constants
import ayman.issa.hostelworld.remote.api.CurrencyExchangeService
import ayman.issa.hostelworld.remote.api.PropertiesService
import ayman.issa.hostelworld.remote.interceptors.CacheInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @JvmStatic
    fun provideOkHttp(): Call.Factory {
        return OkHttpClient.Builder().apply {
            addInterceptor(CacheInterceptor())
        }.build()
    }

    @Provides
    @JvmStatic
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @JvmStatic
    fun provideBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Provides
    @JvmStatic
    fun provideRetrofitForApi(
        moshi: Moshi,
        callFactory: Call.Factory,
        baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory(callFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }

    @Singleton
    @Provides
    fun providePropertiesListService(retrofit: Retrofit): PropertiesService =
        retrofit.create(PropertiesService::class.java)

    @Singleton
    @Provides
    fun provideCurrencyExchangeService(retrofit: Retrofit): CurrencyExchangeService =
        retrofit.create(CurrencyExchangeService::class.java)

}
