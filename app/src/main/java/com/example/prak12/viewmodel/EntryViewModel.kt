package com.example.prak12.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prak12.modeldata.DataSiswa
import com.example.prak12.repositori.RepositoriDataSiswa
import kotlinx.coroutines.launch

class EntryViewModel(private val repositoriDataSiswa: RepositoriDataSiswa) : ViewModel() {
    var uiStateSiswa by mutableStateOf(SiswaUIState())
        private set

    fun updateUiState(siswaEvent: SiswaEvent) {
        uiStateSiswa = SiswaUIState(siswaEvent = siswaEvent)
    }

    suspend fun insertSiswa() {
        viewModelScope.launch {
            try {
                repositoriDataSiswa.postDataSiswa(uiStateSiswa.siswaEvent.toDataSiswa())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

// Data class pendukung untuk State dan Event
data class SiswaUIState(
    val siswaEvent: SiswaEvent = SiswaEvent(),
    val isEntryValid: Boolean = false
)

data class SiswaEvent(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

// Fungsi Mapper untuk konversi data
fun SiswaEvent.toDataSiswa(): DataSiswa = DataSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

fun DataSiswa.toUiStateSiswa(): SiswaUIState = SiswaUIState(
    siswaEvent = this.toSiswaEvent()
)

fun DataSiswa.toSiswaEvent(): SiswaEvent = SiswaEvent(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)