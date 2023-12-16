package com.intinv.intinvapp.sections.portfolio.domain

import com.intinv.intinvapp.domain.DataPortfolio

data class PortfolioScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isMessage: Boolean = false,
    val message: String = "",
    val portfolioData: DataPortfolio? = null
)
