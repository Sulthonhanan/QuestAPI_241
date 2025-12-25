package com.example.prak12.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prak12.R
import com.example.prak12.uicontroller.route.DestinasiEntry
import com.example.prak12.viewmodel.EntryViewModel
import com.example.prak12.viewmodel.SiswaEvent
import com.example.prak12.viewmodel.SiswaUIState
import com.example.prak12.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntrySiswaScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiEntry.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            siswaUIState = viewModel.uiStateSiswa,
            onSiswaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertSiswa()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun EntryBody(
    siswaUIState: SiswaUIState,
    onSiswaValueChange: (SiswaEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        FormInput(
            siswaEvent = siswaUIState.siswaEvent,
            onValueChange = onSiswaValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    siswaEvent: SiswaEvent,
    modifier: Modifier = Modifier,
    onValueChange: (SiswaEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = siswaEvent.nama,
            onValueChange = { onValueChange(siswaEvent.copy(nama = it)) },
            label = { Text(stringResource(R.string.nama)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = siswaEvent.alamat,
            onValueChange = { onValueChange(siswaEvent.copy(alamat = it)) },
            label = { Text(stringResource(R.string.alamat)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = siswaEvent.telpon,
            onValueChange = { onValueChange(siswaEvent.copy(telpon = it)) },
            label = { Text(stringResource(R.string.telpon)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}