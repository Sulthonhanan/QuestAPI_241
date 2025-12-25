package com.example.prak12.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prak12.repositori.RepositoriDataSiswa
import com.example.prak12.uicontroller.route.DestinasiEdit
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoriDataSiswa
) : ViewModel() {

    // Menggunakan ITEM_ID_ARG sesuai perubahan di DestinasiEdit
    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiEdit.ITEM_ID_ARG])

    var editSiswaUiState by mutableStateOf(SiswaUIState())
        private set

    init {
        viewModelScope.launch {
            try {
                // Ambil data lama dan masukkan ke state form
                val siswa = repositoryDataSiswa.getSatuSiswa(idSiswa)
                editSiswaUiState = siswa.toUiStateSiswa()
            } catch (e: Exception) {
                // Tangani error loading data
            }
        }
    }

    fun updateSiswaState(siswaEvent: SiswaEvent) {
        editSiswaUiState = editSiswaUiState.copy(siswaEvent = siswaEvent)
    }

    fun editSiswa() {
        viewModelScope.launch {
            try {
                // Kirim data baru ke API
                repositoryDataSiswa.editSatuSiswa(idSiswa, editSiswaUiState.siswaEvent.toDataSiswa())
            } catch (e: Exception) {
                // Tangani error update
            }
        }
    }
}