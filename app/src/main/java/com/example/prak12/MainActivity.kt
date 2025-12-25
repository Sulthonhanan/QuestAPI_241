package com.example.prak12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.prak12.ui.theme.Prak12Theme
import com.example.prak12.uicontroller.HostNavigasi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Prak12Theme {
                // Panggil fungsi pembungkus utama
                DataSiswaApp()
            }
        }
    }
}

@Composable
fun DataSiswaApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController() // Menginisialisasi controller navigasi
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        // Memanggil HostNavigasi yang berisi rute Home, Detail, dan Edit
        HostNavigasi(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}