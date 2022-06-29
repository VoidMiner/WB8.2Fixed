package com.ands.wb5weekweb.di

import com.ands.wb5weekweb.repository.Repository
import com.ands.wb5weekweb.usecases.SuperHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetHeroesUseCase(repository: Repository): SuperHeroesUseCase {
        return SuperHeroesUseCase(repository = repository)
    }

}