package com.rodcollab.androidrodrigocavalcante.ui.orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodcollab.androidrodrigocavalcante.model.OrderHistory
import com.rodcollab.androidrodrigocavalcante.repository.OrdersHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OrdersHistoryUiState(
    val history: List<OrderHistory> = listOf()
)

@HiltViewModel
class OrdersHistoryVm @Inject constructor(private val repository: OrdersHistoryRepository): ViewModel(){

    val uiState: MutableLiveData<OrdersHistoryUiState> by lazy { MutableLiveData(OrdersHistoryUiState()) }

    fun onResume() {
        viewModelScope.launch {
            val history = async {
                repository.getOrdersHistory()
            }.await()
            uiState.value = uiState.value?.copy(history = history)
        }
    }
}