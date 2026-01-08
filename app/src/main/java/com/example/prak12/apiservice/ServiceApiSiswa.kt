package com.example.prak12.apiservice

import com.example.prak12.modeldata.DataSiswa
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory // Pastikan baris ini ada
import retrofit2.http.*

interface ServiceApiSiswa {
    @GET("bacaTeman.php")
    suspend fun getDataSiswa(): List<DataSiswa>

    @POST("insertTM.php")
    suspend fun postDataSiswa(@Body dataSiswa: DataSiswa): Response<Void>

    @GET("baca1Teman.php")
    suspend fun getSatuSiswa(@Query("id") id: Int): DataSiswa

    @PUT("editTM.php")
    suspend fun editSatuSiswa(@Query("id") id: Int, @Body dataSiswa: DataSiswa): Response<Void>

    @DELETE("deleteTM.php")
    suspend fun hapusSatuSiswa(@Query("id") id: Int): Response<Void>

    companion object {

        private const val BASE_URL = "http://10.0.2.2/Praktikum_12/"

        val instance: ServiceApiSiswa by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServiceApiSiswa::class.java)
        }
    }
}