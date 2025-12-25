package com.example.prak12.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prak12.modeldata.DataSiswa
import com.example.prak12.repositori.RepositoriDataSiswa
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Menggunakan nama StatusUI agar sinkron dengan HomeScreen
sealed interface StatusUI {
    data class Success(val siswa: List<DataSiswa>) : StatusUI
    object Error : StatusUI
    object Loading : StatusUI
}

class HomeViewModel(private val repositoryDataSiswa: RepositoriDataSiswa) : ViewModel() {

    // Mengubah listSiswa menjadi siswaUIState agar sesuai dengan pemanggilan di HomeScreen
    var siswaUIState: StatusUI by mutableStateOf(StatusUI.Loading)
        private set

    init {
        getSiswa()
    }

    fun getSiswa() {
        viewModelScope.launch {
            siswaUIState = StatusUI.Loading
            siswaUIState = try {
                // Memanggil fungsi getDataSiswa dari repositori
                StatusUI.Success(repositoryDataSiswa.getDataSiswa())
            } catch (e: IOException) {
                StatusUI.Error
            } catch (e: HttpException) {
                StatusUI.Error
            }
        }
    }

    // Menambahkan fungsi deleteSiswa agar IconButton di HomeScreen berfungsi
    fun deleteSiswa(id: Int) {
        viewModelScope.launch {
            try {
                repositoryDataSiswa.hapusSatuSiswa(id)
                getSiswa() // Refresh data setelah menghapus
            } catch (e: IOException) {
                siswaUIState = StatusUI.Error
            } catch (e: HttpException) {
                siswaUIState = StatusUI.Error
            }
        }
    }
}