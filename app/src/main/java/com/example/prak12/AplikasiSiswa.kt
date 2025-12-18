package com.example.prak12

import android.app.Application
import com.example.prak12.repositori.ContainerApp
import com.example.prak12.repositori.DefaultContainerApp

class AplikasiSiswa : Application() {

    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = DefaultContainerApp()
    }
}