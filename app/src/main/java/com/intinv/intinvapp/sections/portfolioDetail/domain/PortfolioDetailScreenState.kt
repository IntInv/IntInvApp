package com.intinv.intinvapp.sections.portfolioDetail.domain

import com.intinv.intinvapp.domain.PortfolioDetail

data class PortfolioDetailScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isMessage: Boolean = false,
    val message: String = "",
    val portfolioDetailData: PortfolioDetail? = null
)
