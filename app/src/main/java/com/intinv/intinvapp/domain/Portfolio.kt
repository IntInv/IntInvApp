package com.intinv.intinvapp.domain


data class Portfolio(
    val fullValue: Double,
    val fullProfLossValue: Double,
    val captioValue: Double,
    val openValue: Double,
    val listTicket: List<PortfolioTicket>
)