package com.example.weatherapp.di

import com.example.weatherapp.data.network.NetworkService
import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.data.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkService() = NetworkService.getInstance()

    @Provides
    @Singleton
    fun provideWeatherApi(networkService: NetworkService) =
        networkService.weatherApi

    @Singleton
    @Provides
    fun provideWeatherRepository(weatherApi: WeatherApi) = WeatherRepository(weatherApi)


}