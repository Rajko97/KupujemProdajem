package com.quable.data

import com.quable.data.assets.AssetsModule
import com.quable.domain.AdRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [AssetsModule::class])
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Singleton
    @Binds
    fun bindAdsRepository(adRepository: AdRepositoryImpl): AdRepository
}
