package com.example.prak12.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp // Menghilangkan error 'dp'
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prak12.R
import com.example.prak12.modeldata.DataSiswa
import com.example.prak12.uicontroller.route.DestinasiDetail
import com.example.prak12.viewmodel.DetailViewModel
import com.example.prak12.viewmodel.StatusUIDetail
import com.example.prak12.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSiswaScreen(
    navigateBack: () -> Unit,
    navigateToEditItem: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiDetail.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            val statusUI = viewModel.statusUIDetail
            if (statusUI is StatusUIDetail.Success) {
                FloatingActionButton(
                    onClick = { navigateToEditItem(statusUI.satusiswa.id) },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(16.dp) // Menggunakan nilai dp langsung jika dimen tidak ada
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Siswa")
                }
            }
        }
    ) { innerPadding ->
        ItemDetailBody(
            statusUIDetail = viewModel.statusUIDetail,
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            onDeleteClick = {
                coroutineScope.launch {
                    viewModel.hapusSiswa() // Menyesuaikan nama fungsi di ViewModel
                    navigateBack()
                }
            }
        )
    }
}

@Composable
private fun ItemDetailBody(
    statusUIDetail: StatusUIDetail,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

        when (statusUIDetail) {
            is StatusUIDetail.Loading -> Text("Loading...")
            is StatusUIDetail.Success -> {
                ItemDetail(siswa = statusUIDetail.satusiswa, modifier = Modifier.fillMaxWidth())
                OutlinedButton(
                    onClick = { deleteConfirmationRequired = true },
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.delete))
                }
            }
            is StatusUIDetail.Error -> Text("Terjadi Kesalahan")
        }

        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDeleteClick()
                },
                onDeleteCancel = { deleteConfirmationRequired = false }
            )
        }
    }
}

@Composable
fun ItemDetail(siswa: DataSiswa, modifier: Modifier = Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    )) {
        Column(modifier = Modifier.padding(16.dp)) {
            ItemDetailRow(label = stringResource(R.string.nama), value = siswa.nama)
            ItemDetailRow(label = stringResource(R.string.alamat), value = siswa.alamat)
            ItemDetailRow(label = stringResource(R.string.telpon), value = siswa.telpon)
        }
    }
}

@Composable
private fun ItemDetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Spacer(Modifier.weight(1f))
        Text(text = value)
    }
}

@Composable
private fun DeleteConfirmationDialog(onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(stringResource(R.string.attention)) },
        text = { Text(stringResource(R.string.delete_confirmation)) },
        dismissButton = { TextButton(onClick = onDeleteCancel) { Text(stringResource(R.string.no)) } },
        confirmButton = { TextButton(onClick = onDeleteConfirm) { Text(stringResource(R.string.yes)) } }
    )
}