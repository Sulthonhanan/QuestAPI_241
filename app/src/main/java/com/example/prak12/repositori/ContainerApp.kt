package com.example.prak12.repositori

import android.content.Context
import com.example.prak12.apiservice.ServiceApiSiswa

interface ContainerApp {
    val repositoriDataSiswa: RepositoriDataSiswa
}

class DefaultContainerApp(private val context: Context) : ContainerApp { // Pastikan menerima context
    override val repositoriDataSiswa: RepositoriDataSiswa by lazy {
        JaringanRepositoriDataSiswa(ServiceApiSiswa.instance) // Perbaikan pemanggilan instance
    }
}