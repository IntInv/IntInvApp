package com.intinv.intinvapp.sections.transaction.repository

import android.util.Log
import com.intinv.intinvapp.LOG_TAG
import com.intinv.intinvapp.domain.ResultState
import com.intinv.intinvapp.domain.Status
import com.intinv.intinvapp.domain.Transaction
import com.intinv.intinvapp.sections.transaction.network.AddTransactionService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddTransactionRepository @Inject constructor(
    private val api: AddTransactionService
) {
    suspend fun setAddTransaction(transaction: Transaction) {
        try {
            Log.d(LOG_TAG, "setAddTransactionInfo")
            val response = api.setAddTransactionInfo(transaction)
        }
        catch (e:Exception){
            Log.d(LOG_TAG, "ERROR setAddTransactionInfo " + e.message)
        }
    }
}
//
//    = flow{
//        emit(ResultState(Status.LOADING))
//        try{
//            val response = api.setAddTransactionInfo(transaction)
//            if (response.isSuccessful){
//                if(response.body() != null){
//                    emit(ResultState(Status.SUCCESS, response.body()))
//                }
//                else {
//                    emit(
//                        ResultState(
//                            Status.ERROR,
//                            "Тело ответа на запрос данных о котировках."
//                        )
//                    )
//                }
//            }
//            else{
//                emit(
//                    ResultState(
//                        Status.ERROR,
//                        "Сервер вернул ошибку с кодом ${response.code()} на запрос информации о котировках."
//                    )
//                )
//            }
//        }
//        catch(e: Exception) {
//            emit(
//                ResultState(
//                    Status.ERROR,
//                    "Ошибка при совершении запроса информации о котировках: ${e.message}"
//                )
//            )
//        }
//    }
//}