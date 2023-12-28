package com.intinv.intinvapp.sections.quotes.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intinv.intinvapp.constants.DEFAULT_NETWORK_ERROR_TEXT
import com.intinv.intinvapp.constants.DEFAULT_NETWORK_LOADING_TEXT
import com.intinv.intinvapp.domain.Quotes
import com.intinv.intinvapp.domain.Status
import com.intinv.intinvapp.domain.Ticket
import com.intinv.intinvapp.sections.quotes.domain.LoadQuotes
import com.intinv.intinvapp.sections.quotes.domain.QuotesScreenIntent
import com.intinv.intinvapp.sections.quotes.domain.QuotesScreenState
import com.intinv.intinvapp.sections.quotes.repository.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val repository: QuotesRepository
) : ViewModel() {
    private val _screenState: MutableStateFlow<QuotesScreenState> = MutableStateFlow(
        QuotesScreenState()
    )
    val screenState: StateFlow<QuotesScreenState> = _screenState

    init {
        loadQuotesDataFromNet()
    }

    fun handleIntent(intent: QuotesScreenIntent) {
        when (intent) {
            LoadQuotes -> {
                loadQuotesDataFromNet()
            }
        }
    }

    private fun loadQuotesDataFromNet() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotesRemote().collect {
                val resultMessage = if (it.data is String) it.data else null
                when (it.status) {
                    Status.SUCCESS -> {
                        if (it.data is List<*>) {
                            if(it.data.firstOrNull() is Ticket) {
                                _screenState.value = _screenState.value.copy(
                                    isLoading = false,
                                    isError = false,
                                    isMessage = false,
                                    message = resultMessage ?: DEFAULT_NETWORK_LOADING_TEXT,
                                    quotesData = Quotes(
                                        data = it.data as List<Ticket>
                                    )
                                )
                            }
                        } else {
                            _screenState.value = _screenState.value.copy(
                                isLoading = false,
                                isError = true,
                                isMessage = false,
                                message = "Получены некорректные данные"
                            )
                        }
                    }
                    Status.LOADING -> {
                        _screenState.value = _screenState.value.copy(
                            isLoading = true,
                            isError = false,
                            isMessage = false,
                            message = resultMessage ?: DEFAULT_NETWORK_LOADING_TEXT
                        )
                    }
                    Status.ERROR -> {
                        _screenState.value = _screenState.value.copy(
                            isLoading = false,
                            isError = true,
                            isMessage = false,
                            message = resultMessage ?: DEFAULT_NETWORK_ERROR_TEXT
                        )
                    }
                }
            }
        }
    }
}