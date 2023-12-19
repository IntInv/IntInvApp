package com.intinv.intinvapp.sections.portfolioDetail.network

import com.intinv.intinvapp.domain.PortfolioDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PortfolioDetailService {
    @GET("portfolio/{label}")
    suspend fun getPortfolioDetailInfo(@Path("label") label: String): Response<PortfolioDetail>
}