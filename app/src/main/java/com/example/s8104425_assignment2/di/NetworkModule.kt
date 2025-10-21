package com.example.s8104425_assignment2.di

import com.example.s8104425_assignment2.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Hilt module to provide network dependencies
@Module
@InstallIn(SingletonComponent::class) //lives as long as app process
object NetworkModule {

    private const val BASE_URL = "https://nit3213api.onrender.com/"

    //provides a Retrofit instance
    @Provides
    @Singleton //ensures single instance across app --> avoid recreating Retrofit multiple times
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)//base Api url
            .addConverterFactory(GsonConverterFactory.create()) //parse JSON response
            .build()
    }

    //provides the ApiService interface implementation
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java) //Retrofit generates code for endpoint
    }
}
