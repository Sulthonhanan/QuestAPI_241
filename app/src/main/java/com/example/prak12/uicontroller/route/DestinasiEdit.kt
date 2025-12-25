package com.example.prak12.uicontroller.route

import com.example.prak12.R

object DestinasiEdit : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa

    const val ITEM_ID_ARG = "idSiswa"
    val routeWithArgs = "$route/{$ITEM_ID_ARG}"
}