package com.example.prak12.repositori

import android.app.Application
import com.example.prak12.apiservice.ServiceApiSiswa
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface ContainerApp {
    val repositoriDataSiswa: RepositoriDataSiswa //
}

class DefaultContainerApp : ContainerApp {
    private val baseurl = "http:// 192.168.101.70/Praktikum_12/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val klien = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .client(klien) // Menambahkan klien OkHttp agar logging berfungsi
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService: ServiceApiSiswa by lazy {
        retrofit.create(ServiceApiSiswa::class.java)
    }

    override val repositoriDataSiswa: RepositoriDataSiswa by lazy {
        JaringanRepositoriDataSiswa(retrofitService)
    }
}