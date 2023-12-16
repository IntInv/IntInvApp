package com.intinv.intinvapp.sections.portfolio.network

import com.intinv.intinvapp.domain.DataPortfolio
import retrofit2.Response
import retrofit2.http.GET

interface PortfolioService {
    @GET("potrfolio")
    suspend fun getPortfolioInfo(): Response<DataPortfolio>
}