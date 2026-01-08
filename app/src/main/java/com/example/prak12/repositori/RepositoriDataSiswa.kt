package com.example.prak12.repositori

import com.example.prak12.apiservice.ServiceApiSiswa
import com.example.prak12.modeldata.DataSiswa
import java.io.IOException

interface RepositoriDataSiswa {
    suspend fun getDataSiswa(): List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa)
    suspend fun editSatuSiswa(id: Int, dataSiswa: DataSiswa)
    suspend fun hapusSatuSiswa(id: Int)
    suspend fun getSatuSiswa(id: Int): DataSiswa
}

class JaringanRepositoriDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
) : RepositoriDataSiswa {
    override suspend fun getDataSiswa(): List<DataSiswa> = serviceApiSiswa.getDataSiswa()

    override suspend fun postDataSiswa(dataSiswa: DataSiswa) {
        val response = serviceApiSiswa.postDataSiswa(dataSiswa)
        if (!response.isSuccessful) throw IOException("Gagal input data")
    }

    override suspend fun editSatuSiswa(id: Int, dataSiswa: DataSiswa) {
        val response = serviceApiSiswa.editSatuSiswa(id, dataSiswa)
        if (!response.isSuccessful) throw IOException("Gagal update data")
    }

    override suspend fun hapusSatuSiswa(id: Int) {
        val response = serviceApiSiswa.hapusSatuSiswa(id)
        if (!response.isSuccessful) throw IOException("Gagal hapus data")
    }

    override suspend fun getSatuSiswa(id: Int): DataSiswa = serviceApiSiswa.getSatuSiswa(id)
}