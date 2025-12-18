package com.example.prak12.apiservice

import com.example.prak12.modeldata.DataSiswa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceApiSiswa {
    @GET("bacateman.php")
    suspend fun getDataSiswa(): List<DataSiswa> // Nama disamakan dengan pemanggilan di HomeViewModel

    @POST("insertTM.php")
    suspend fun postDataSiswa(@Body dataSiswa: DataSiswa): Response<Void> // Nama disamakan dengan pemanggilan di EntryViewModel
}