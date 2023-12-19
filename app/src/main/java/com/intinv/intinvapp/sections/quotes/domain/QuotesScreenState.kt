package com.intinv.intinvapp.sections.quotes.domain

import com.intinv.intinvapp.domain.Quotes

data class QuotesScreenState (
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isMessage: Boolean = false,
    val message: String = "",
    val quotesData: Quotes? = null
)