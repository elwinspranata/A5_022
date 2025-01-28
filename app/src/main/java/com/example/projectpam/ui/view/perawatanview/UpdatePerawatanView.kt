package com.example.projectpam.ui.view.perawatanview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.projectpam.ui.costumwidget.CostumeTopAppBar
import com.example.projectpam.ui.navigation.DestinasiNavigasi
import com.example.projectpam.ui.viewmodel.PenyediaViewModel
import com.example.projectpam.ui.viewmodel.PerawatanViewModel.UpdatePerawatanViewModel
import com.example.projectpam.ui.viewmodel.PerawatanViewModel.UpdateUiEvent
import com.example.projectpam.ui.viewmodel.PerawatanViewModel.UpdateUiState
import kotlinx.coroutines.launch

object DestinasiUpdatePerawatan : DestinasiNavigasi {
    override val route = "update perawatan"
    override val titleRes = "Update Perawatan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePerawatanView(
    idPerawatan: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdatePerawatanViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(idPerawatan) {
        viewModel.loadPerawatan(idPerawatan)
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdatePerawatan.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        UpdatePerawatanBody(
            updateUiState = viewModel.uiState,
            onPerawatanValueChange = viewModel::updatePerawatanState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePerawatan(idPerawatan)
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun UpdatePerawatanBody(
    updateUiState: UpdateUiState,
    onPerawatanValueChange: (UpdateUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            updateUiEvent = updateUiState.updateUiEvent,
            onValueChange = onPerawatanValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Update")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    updateUiEvent: UpdateUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdateUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = updateUiEvent.idPerawatan,
            onValueChange = { onValueChange(updateUiEvent.copy(idPerawatan = it)) },
            label = { Text("Id Perawatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.idHewan,
            onValueChange = { onValueChange(updateUiEvent.copy(idHewan = it)) },
            label = { Text("Id Hewan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.idDokter,
            onValueChange = { onValueChange(updateUiEvent.copy(idDokter = it)) },
            label = { Text("Id Dokter") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.tanggalPerawatan,
            onValueChange = { onValueChange(updateUiEvent.copy(tanggalPerawatan = it)) },
            label = { Text("Tanggal Perawatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.detailPerawatan,
            onValueChange = { onValueChange(updateUiEvent.copy(detailPerawatan = it)) },
            label = { Text("Detail Perawatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}
