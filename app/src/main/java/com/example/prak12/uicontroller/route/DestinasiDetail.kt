package com.example.prak12.uicontroller.route

import com.example.prak12.R

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail_siswa"
    override val titleRes = R.string.detail_siswa

    // Konstanta kunci argumen
    const val ITEM_ID_ARG = "idSiswa"
    val routeWithArgs = "$route/{$ITEM_ID_ARG}"
}