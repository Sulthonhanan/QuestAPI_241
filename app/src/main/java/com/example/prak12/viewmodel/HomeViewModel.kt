package com.example.prak12.viewmodel

import android.util.Log // Tambahkan import ini agar Log.e tidak merah
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prak12.modeldata.DataSiswa
import com.example.prak12.repositori.RepositoriDataSiswa
import kotlinx.coroutines.launch

sealed interface HomeUiState {
    data class Success(val siswa: List<DataSiswa> = listOf()) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

class HomeViewModel(private val repositoriDataSiswa: RepositoriDataSiswa) : ViewModel() {
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getSiswa()
    }

    fun getSiswa() {
        viewModelScope.launch {
            homeUiState = HomeUiState.Loading
            homeUiState = try {
                HomeUiState.Success(siswa = repositoriDataSiswa.getDataSiswa())
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error: ${e.message}")
                HomeUiState.Error
            }
        }
    }
}