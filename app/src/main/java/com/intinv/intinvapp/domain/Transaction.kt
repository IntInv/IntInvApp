package com.intinv.intinvapp.domain

import java.util.Calendar

data class Transaction(
    val type: String,
    val name: String,
    val quantity: Int,
    val dateTransaction: Calendar,
    val price: Double
)
