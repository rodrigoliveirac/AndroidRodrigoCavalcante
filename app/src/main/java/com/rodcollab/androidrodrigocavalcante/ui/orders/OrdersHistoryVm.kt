package com.rodcollab.androidrodrigocavalcante.ui.orders

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodcollab.androidrodrigocavalcante.model.OrderHistory
import com.rodcollab.androidrodrigocavalcante.network.MaxApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OrdersHistoryUiState(
    val history: List<OrderHistory> = listOf()
)

@HiltViewModel
class OrdersHistoryVm @Inject constructor(private val maxApi:MaxApi): ViewModel(){

    val uiState: MutableLiveData<OrdersHistoryUiState> by lazy { MutableLiveData(OrdersHistoryUiState()) }

    fun onResume() {
        viewModelScope.launch {
            val data = maxApi.getOrdersHistory()
            if(data?.isSuccessful == true){
                uiState.value = data.body()?.pedidos?.let { uiState.value?.copy(history = it) }
                Log.d("pedidos_data", data.body()?.pedidos.toString())
            }
        }
    }
}