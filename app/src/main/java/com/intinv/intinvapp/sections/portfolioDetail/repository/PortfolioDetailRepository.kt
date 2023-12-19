package com.intinv.intinvapp.sections.portfolioDetail.repository

import com.intinv.intinvapp.domain.ResultState
import com.intinv.intinvapp.domain.Status
import com.intinv.intinvapp.sections.portfolioDetail.network.PortfolioDetailService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PortfolioDetailRepository @Inject constructor(
    private val api: PortfolioDetailService
) {
    suspend fun getPortfolioDetailRemote(label: String) = flow {
        emit(ResultState(Status.LOADING))
        try {
            val response = api.getPortfolioDetailInfo(label)
            if (response.isSuccessful) {
                if(response.body() != null) {
                    emit(ResultState(Status.SUCCESS, response.body()))
                } else {
                    emit(ResultState(Status.ERROR, "Тело ответа на запрос деталей портфеля пусто."))
                }
            } else {
                emit(
                    ResultState(
                        Status.ERROR,
                        "Сервер вернул ошибку с кодом ${response.code()} на запрос деталей портфеля."
                    )
                )
            }
        } catch (e: Exception) {
            emit(
                ResultState(
                    Status.ERROR,
                    "Ошибка при совершении запроса информации о деталях портфеля : ${e.message}"
                )
            )
        }
    }
}


