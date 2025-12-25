package com.example.prak12.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.prak12.AplikasiSiswa
import com.example.prak12.viewmodel.DetailViewModel
import com.example.prak12.viewmodel.EditViewModel
import com.example.prak12.viewmodel.EntryViewModel
import com.example.prak12.viewmodel.HomeViewModel

fun CreationExtras.aplikasiSiswa(): AplikasiSiswa = (
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa
        )

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiSiswa().container.repositoriDataSiswa)
        }
        initializer {
            EntryViewModel(aplikasiSiswa().container.repositoriDataSiswa)
        }
        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                aplikasiSiswa().container.repositoriDataSiswa
            )
        }
        initializer {
            EditViewModel(
                this.createSavedStateHandle(),
                aplikasiSiswa().container.repositoriDataSiswa
            )
        }
    }
}