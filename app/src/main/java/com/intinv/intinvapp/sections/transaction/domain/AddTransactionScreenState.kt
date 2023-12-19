package com.intinv.intinvapp.sections.transaction.domain

import com.intinv.intinvapp.domain.Transaction

data class AddTransactionScreenState (
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isMessage: Boolean = false,
    val message: String = "",
    val transactionData: Transaction? = null
)