package com.example.prak12.uicontroller.route

import com.example.prak12.R

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail_siswa"
    override val titleRes = R.string.detail_siswa
    const val ITEM_ID_ARG = "idSiswa" // Pastikan menggunakan ITEM_ID_ARG
    val routeWithArgs = "$route/{$ITEM_ID_ARG}"
}