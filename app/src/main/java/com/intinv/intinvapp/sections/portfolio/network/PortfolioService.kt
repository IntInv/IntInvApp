package com.intinv.intinvapp.sections.portfolio.network

import com.intinv.intinvapp.domain.Portfolio
import retrofit2.Response
import retrofit2.http.GET

interface PortfolioService {
    @GET("portfolio")
    suspend fun getPortfolioInfo(): Response<Portfolio>
}