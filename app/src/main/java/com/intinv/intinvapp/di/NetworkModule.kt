package com.intinv.intinvapp.di

import com.intinv.intinvapp.BuildConfig
import com.intinv.intinvapp.sections.portfolio.network.PortfolioService
import com.intinv.intinvapp.sections.portfolioDetail.network.PortfolioDetailService
import com.intinv.intinvapp.sections.quotes.network.QuotesService
import com.intinv.intinvapp.sections.transaction.network.AddTransactionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        // TODO - add auth interceptor

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePortfolioService(retrofit: Retrofit): PortfolioService {
        return retrofit.create(PortfolioService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuotesService(retrofit: Retrofit): QuotesService {
        return retrofit.create(QuotesService::class.java)
    }

    @Provides
    @Singleton
    fun providePortfolioDetailService(retrofit: Retrofit): PortfolioDetailService {
        return retrofit.create(PortfolioDetailService::class.java)
    }

    @Provides
    @Singleton
    fun provideAddTransactionService(retrofit: Retrofit): AddTransactionService {
        return retrofit.create(AddTransactionService::class.java)
    }
}