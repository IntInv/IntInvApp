package com.intinv.intinvapp.sections.portfolio.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intinv.intinvapp.constants.DEFAULT_NETWORK_ERROR_TEXT
import com.intinv.intinvapp.constants.DEFAULT_NETWORK_LOADING_TEXT
import com.intinv.intinvapp.domain.Portfolio
import com.intinv.intinvapp.domain.Status
import com.intinv.intinvapp.sections.portfolio.domain.LoadPortfolio
import com.intinv.intinvapp.sections.portfolio.domain.PortfolioScreenIntent
import com.intinv.intinvapp.sections.portfolio.domain.PortfolioScreenState
import com.intinv.intinvapp.sections.portfolio.repository.PortfolioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val repository: PortfolioRepository
) : ViewModel() {
    private val _screenState: MutableStateFlow<PortfolioScreenState> = MutableStateFlow(PortfolioScreenState())
    val screenState: StateFlow<PortfolioScreenState> = _screenState

    init {
        loadPortfolioDataFromNet()
    }

    fun handleIntent(intent: PortfolioScreenIntent) {
        when (intent) {
            LoadPortfolio -> {
                loadPortfolioDataFromNet()
            }
        }
    }

    private fun loadPortfolioDataFromNet() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPortfolioRemote().collect {
                val resultMessage = if (it.data is String) it.data else null
                when (it.status) {
                    Status.SUCCESS -> {
                        if (it.data is Portfolio) {
                            _screenState.value = _screenState.value.copy(
                                isLoading = false,
                                isError = false,
                                isMessage = false,
                                message = resultMessage ?: DEFAULT_NETWORK_LOADING_TEXT,
                                portfolioData = it.data
                            )
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