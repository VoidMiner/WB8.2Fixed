package com.ands.wb5weekweb.di

import android.content.Context
import com.ands.wb5weekweb.api.ApiServiceSuperHeroes
import com.ands.wb5weekweb.repository.Repository
import com.ands.wb5weekweb.repository.RepositoryImpl
import com.ands.wb5weekweb.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule() {

    @Provides
    @Singleton
    fun provideApiService(): ApiServiceSuperHeroes {
        return Retrofit.Builder()
            .baseUrl(Constants.SUPER_HEROES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceSuperHeroes::class.java)
    }



    @Provides
    @Singleton
    fun provideRepository(
        @ApplicationContext context: Context,
        apiServiceSuperHeroes: ApiServiceSuperHeroes,
    ): Repository {
        return RepositoryImpl(
            context = context,
            apiServiceSuperHeroes = apiServiceSuperHeroes,


        )
    }
}