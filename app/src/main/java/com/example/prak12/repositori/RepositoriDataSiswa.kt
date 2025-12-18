package com.example.prak12.repositori

import com.example.prak12.apiservice.ServiceApiSiswa
import com.example.prak12.modeldata.DataSiswa
import retrofit2.Response

interface RepositoriDataSiswa {
    suspend fun getDataSiswa(): List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void>
}

class JaringanRepositoriDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
) : RepositoriDataSiswa {
    override suspend fun getDataSiswa(): List<DataSiswa> =
        serviceApiSiswa.getDataSiswa() // Menyesuaikan nama fungsi di ServiceApiSiswa

    override suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void> =
        serviceApiSiswa.postDataSiswa(dataSiswa) // Menyesuaikan nama fungsi di ServiceApiSiswa
}