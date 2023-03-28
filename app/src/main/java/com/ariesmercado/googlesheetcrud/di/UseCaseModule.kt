package com.ariesmercado.googlesheetcrud.di

import com.ariesmercado.googlesheetcrud.data.repository.GoogleSheetRepository
import com.ariesmercado.googlesheetcrud.domain.use_case.DeleteEmployeeUseCase
import com.ariesmercado.googlesheetcrud.domain.use_case.GetGoogleSheetUseCase
import com.ariesmercado.googlesheetcrud.domain.use_case.RegisterEmployeeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetGoogleSheetUseCase(repository: GoogleSheetRepository) = GetGoogleSheetUseCase(repository)

    @Provides
    @Singleton
    fun provideRegisterEmployeeUseCase(repository: GoogleSheetRepository) = RegisterEmployeeUseCase(repository)

    @Provides
    @Singleton
    fun provideDeleteEmployeeUseCase(repository: GoogleSheetRepository) = DeleteEmployeeUseCase(repository)

}