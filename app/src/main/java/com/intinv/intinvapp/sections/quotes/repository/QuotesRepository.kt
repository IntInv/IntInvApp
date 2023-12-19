package com.intinv.intinvapp.sections.quotes.repository

import com.intinv.intinvapp.domain.ResultState
import com.intinv.intinvapp.domain.Status
import com.intinv.intinvapp.sections.quotes.network.QuotesService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuotesRepository @Inject constructor(
    private val api: QuotesService
) {
    suspend fun getQuotesRemote() = flow {
        emit(ResultState(Status.LOADING))
        try{
            val response = api.getQuotesInfo()
            if (response.isSuccessful){
                if(response.body() != null){
                    emit(ResultState(Status.SUCCESS, response.body()))
                }
                else {
                    emit(
                        ResultState(
                            Status.ERROR,
                            "Тело ответа на запрос данных о котировках."
                        )
                    )
                }
            }
            else{
                emit(
                    ResultState(
                        Status.ERROR,
                        "Сервер вернул ошибку с кодом ${response.code()} на запрос информации о котировках."
                    )
                )
            }
        }
        catch(e: Exception) {
            emit(
                ResultState(
                    Status.ERROR,
                    "Ошибка при совершении запроса информации о котировках: ${e.message}"
                )
            )
        }
    }
}