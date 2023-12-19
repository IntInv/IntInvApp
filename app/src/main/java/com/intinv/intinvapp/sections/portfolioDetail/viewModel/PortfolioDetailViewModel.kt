package com.intinv.intinvapp.sections.portfolioDetail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intinv.intinvapp.constants.DEFAULT_NETWORK_ERROR_TEXT
import com.intinv.intinvapp.constants.DEFAULT_NETWORK_LOADING_TEXT
import com.intinv.intinvapp.domain.PortfolioDetail
import com.intinv.intinvapp.domain.Status
import com.intinv.intinvapp.sections.portfolioDetail.domain.LoadPortfolioDetail
import com.intinv.intinvapp.sections.portfolioDetail.domain.PortfolioDetailScreenIntent
import com.intinv.intinvapp.sections.portfolioDetail.domain.PortfolioDetailScreenState
import com.intinv.intinvapp.sections.portfolioDetail.repository.PortfolioDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioDetailViewModel  @Inject  constructor(
    private val repository: PortfolioDetailRepository,
//    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _screenState: MutableStateFlow<PortfolioDetailScreenState> = MutableStateFlow(PortfolioDetailScreenState())
    val screenState: StateFlow<PortfolioDetailScreenState> = _screenState

    init {
//        Log.d(LOG_TAG, "Test init")
//        Log.d(LOG_TAG,savedStateHandle.get<String>("detailName") ?:"NULL")
////        val detailName: String = checkNotNull(savedStateHandle["detailName"])
//        val detailName: String = savedStateHandle.get<String>("detailName") ?: "KEK"
        loadPortfolioDetailDataFromNet("")
    }

    fun handleIntent(intent: PortfolioDetailScreenIntent, label: String) {
        when (intent) {
            LoadPortfolioDetail -> {
                loadPortfolioDetailDataFromNet(label)
            }
        }
    }

    private fun loadPortfolioDetailDataFromNet(label: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPortfolioDetailRemote(label).collect {
                val resultMessage = if (it.data is String) it.data else null
                when (it.status) {
                    Status.SUCCESS -> {
                        if (it.data is PortfolioDetail) {
                            _screenState.value = _screenState.value.copy(
                                isLoading = false,
                                isError = false,
                                isMessage = false,
                                message = resultMessage ?: DEFAULT_NETWORK_LOADING_TEXT,
                                portfolioDetailData = it.data
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
//    companion object {
//        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                modelClass: Class<T>,
//                extras: CreationExtras
//            ): T {
//
//                // Create a SavedStateHandle for this ViewModel from extras
//                val savedStateHandle = extras.createSavedStateHandle()
//
//                return PortfolioDetailViewModel(
//                    PortfolioDetailRepository,
//                    savedStateHandle
//                ) as T
//            }
//        }
//    }

}


//@AssistedFactory
//interface PortfolioDetailViewModelFactory {
//    fun create(repository: PortfolioDetailRepository, detailName: String): PortfolioDetailViewModel
//}