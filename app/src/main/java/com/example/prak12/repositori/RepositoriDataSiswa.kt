package com.example.prak12.repositori

import com.example.prak12.apiservice.ServiceApiSiswa
import com.example.prak12.modeldata.DataSiswa
import retrofit2.Response

interface RepositoriDataSiswa {
    suspend fun getDataSiswa(): List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void>
    suspend fun getSatuSiswa(id: Int): DataSiswa
    suspend fun editSatuSiswa(id: Int, dataSiswa: DataSiswa): Response<Void>
    suspend fun hapusSatuSiswa(id: Int): Response<Void>
}

class JaringanRepositoriDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
) : RepositoriDataSiswa {
    // Memperbaiki nama fungsi dari getSiswa ke getDataSiswa
    override suspend fun getDataSiswa(): List<DataSiswa> =
        serviceApiSiswa.getDataSiswa()

    // Memperbaiki nama fungsi dari postSiswa ke postDataSiswa
    override suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void> =
        serviceApiSiswa.postDataSiswa(dataSiswa)

    override suspend fun getSatuSiswa(id: Int): DataSiswa =
        serviceApiSiswa.getSatuSiswa(id)

    override suspend fun editSatuSiswa(id: Int, dataSiswa: DataSiswa): Response<Void> =
        serviceApiSiswa.editSatuSiswa(id, dataSiswa)

    override suspend fun hapusSatuSiswa(id: Int): Response<Void> =
        serviceApiSiswa.hapusSatuSiswa(id)
}