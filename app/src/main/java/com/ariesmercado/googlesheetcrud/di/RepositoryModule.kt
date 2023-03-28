package com.ariesmercado.googlesheetcrud.di


import com.ariesmercado.googlesheetcrud.data.repository.GoogleSheetRepository
import com.ariesmercado.googlesheetcrud.data.repository.GoogleSheetRepositoryImpl
import com.ariesmercado.googlesheetcrud.data.source.remote.service.GoogleSheetApi
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
    fun provideGoogleSheetRepository(api: GoogleSheetApi): GoogleSheetRepository {
        return GoogleSheetRepositoryImpl(api)
    }
}