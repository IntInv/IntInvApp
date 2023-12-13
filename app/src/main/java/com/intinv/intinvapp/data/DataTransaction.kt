package com.intinv.intinvapp.data

import java.util.Calendar

data class DataTransaction(
    val type: String,
    val name: String,
    val quantity: Int,
    val dateTransaction: Calendar,
    val price: Double
)
