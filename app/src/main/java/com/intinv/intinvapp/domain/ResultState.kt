package com.intinv.intinvapp.domain

data class ResultState<T>(
    val status: Status,
    val data: T? = null
)
