package com.ariesmercado.googlesheetcrud.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

//    @Provides
//    @Singleton
//    fun provideGetExchangeRatesUseCase(repository: CurrencyRepository) = GetExchangeRatesUseCase(repository)
//
//    @Provides
//    @Singleton
//    fun provideGetCurrencyBalanceUseCase(repository: CurrencyRepository) = GetCurrencyBalanceUseCase(repository)
//
//    @Provides
//    @Singleton
//    fun provideSetCurrencyBalanceUseCase(repository: CurrencyRepository) = SetCurrencyBalanceUseCase(repository)
//
//    @Provides
//    @Singleton
//    fun provideResetDataUseCase(repository: CurrencyRepository) = ResetDatasUseCase(repository)
}