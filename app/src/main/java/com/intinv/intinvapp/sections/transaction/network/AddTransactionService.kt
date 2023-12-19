package com.intinv.intinvapp.sections.transaction.network

import com.intinv.intinvapp.domain.Transaction
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AddTransactionService {
    @POST("trade")
    suspend fun setAddTransactionInfo(@Body transaction: Transaction): Response<Transaction>
}