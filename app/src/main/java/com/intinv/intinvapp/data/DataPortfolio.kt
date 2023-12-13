package com.intinv.intinvapp.data

data class DataPortfolio (
    val fullValue: Double,
    val fullProfLossValue: Double,
    val captioValue: Double,
    val openValue: Double,
    val listTicket: List<DataTicket>
)