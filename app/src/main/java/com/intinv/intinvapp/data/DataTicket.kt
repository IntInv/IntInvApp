package com.intinv.intinvapp.data

import java.util.Calendar

data class DataTicket (
    val name: String,
    val fullNamr: String,
    val time: Calendar,
    val price: Double,
    val changeDay: Double
)
