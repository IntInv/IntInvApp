package com.intinv.intinvapp.sections.quotes.network

import com.intinv.intinvapp.domain.Quotes
import retrofit2.Response
import retrofit2.http.GET

interface QuotesService {
    @GET("securities")
    suspend fun getQuotesInfo(): Response<Quotes>
}