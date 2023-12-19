package com.intinv.intinvapp.sections.transaction.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intinv.intinvapp.LOG_TAG
import com.intinv.intinvapp.domain.Transaction
import com.intinv.intinvapp.sections.quotes.domain.LoadQuotes
import com.intinv.intinvapp.sections.quotes.domain.QuotesScreenIntent
import com.intinv.intinvapp.sections.quotes.domain.QuotesScreenState
import com.intinv.intinvapp.sections.quotes.repository.QuotesRepository
import com.intinv.intinvapp.sections.transaction.domain.AddTransactionScreenState
import com.intinv.intinvapp.sections.transaction.repository.AddTransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private  val repository: AddTransactionRepository
) : ViewModel(){
    private val _screenState: MutableStateFlow<AddTransactionScreenState> = MutableStateFlow(
        AddTransactionScreenState()
    )
    val screenState: StateFlow<AddTransactionScreenState> = _screenState

    fun handleIntent(transient: Transaction){
        Log.d(LOG_TAG, "send Transaction")
        viewModelScope.launch(Dispatchers.IO){
            repository.setAddTransaction(transient)
        }
    }
}