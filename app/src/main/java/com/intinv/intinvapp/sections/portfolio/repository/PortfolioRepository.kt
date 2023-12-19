package com.intinv.intinvapp.sections.portfolio.repository

import com.intinv.intinvapp.domain.ResultState
import com.intinv.intinvapp.domain.Status
import com.intinv.intinvapp.sections.portfolio.network.PortfolioService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PortfolioRepository @Inject constructor(
    private val api: PortfolioService
) {
    suspend fun getPortfolioRemote() = flow {
        emit(ResultState(Status.LOADING))
        try {
            val response = api.getPortfolioInfo()
            if (response.isSuccessful) {
                if(response.body() != null) {
                    emit(ResultState(Status.SUCCESS, response.body()))
                } else {
                    emit(ResultState(Status.ERROR, "Тело ответа на запрос данных о портфеле пусто."))
                }
            } else {
                emit(
                    ResultState(
                        Status.ERROR,
                        "Сервер вернул ошибку с кодом ${response.code()} на запрос информации о портфеле."
                    )
                )
            }
        } catch (e: Exception) {
            emit(
                ResultState(
                    Status.ERROR,
                    "Ошибка при совершении запроса информации о портфеле: ${e.message}"
                )
            )
        }
    }
}


