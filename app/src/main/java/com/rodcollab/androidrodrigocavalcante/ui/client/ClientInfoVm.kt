package com.rodcollab.androidrodrigocavalcante.ui.client

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodcollab.androidrodrigocavalcante.model.ClientData
import com.rodcollab.androidrodrigocavalcante.network.MaxApi
import com.rodcollab.androidrodrigocavalcante.repository.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject


data class UiState(
    val client: ClientData? = null
)

@HiltViewModel
class ClientInfoVm @Inject constructor(private val repository: ClientRepository) : ViewModel() {

    val uiState: MutableLiveData<UiState> by lazy { MutableLiveData(UiState()) }

    fun onResume() {
        viewModelScope.launch {
            val data = async {
                repository.getClient()
            }.await()

            uiState.value = uiState.value?.copy(client = data)
        }
    }

}