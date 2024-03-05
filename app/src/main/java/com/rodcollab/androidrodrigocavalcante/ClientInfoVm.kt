package com.rodcollab.androidrodrigocavalcante

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


data class UiState(
    val client: ClientData? = null
)

@HiltViewModel
class ClientInfoVm @Inject constructor(private val maxApi: MaxApi) : ViewModel() {

    val uiState: MutableLiveData<UiState> by lazy { MutableLiveData(UiState()) }

    fun onResume() {
        viewModelScope.launch {
            val data = maxApi.clientData()
            if(data?.isSuccessful == true){
                uiState.value = data.body()?.cliente?.let { uiState.value?.copy(client = it) }
                Log.d("client_data", data.body()?.cliente.toString())
            }
        }
    }

}