package com.example.prak12.apiservice

import com.example.prak12.modeldata.DataSiswa
import retrofit2.Response
import retrofit2.http.*

interface ServiceApiSiswa {
    @GET("bacaTeman.php")
    suspend fun getDataSiswa(): List<DataSiswa>

    @POST("insertTM.php")
    suspend fun postDataSiswa(@Body dataSiswa: DataSiswa): Response<Void>

    @GET("baca1Teman.php")
    suspend fun getSatuSiswa(@Query("id") id: Int): DataSiswa

    @PUT("editTM.php")
    suspend fun editSatuSiswa(
        @Query("id") id: Int,
        @Body dataSiswa: DataSiswa
    ): Response<Void>

    @DELETE("deleteTM.php")
    suspend fun hapusSatuSiswa(@Query("id") id: Int): Response<Void>
}