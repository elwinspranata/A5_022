package com.example.projectpam.ui.view.dokterview

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectpam.ui.costumwidget.CostumeTopAppBar
import com.example.projectpam.ui.navigation.DestinasiNavigasi
import com.example.projectpam.ui.viewmodel.DokterViewModel.InsertDokterViewModel
import com.example.projectpam.ui.viewmodel.DokterViewModel.InsertUiEvent
import com.example.projectpam.ui.viewmodel.DokterViewModel.InsertUiState
import com.example.projectpam.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiEntryDokter : DestinasiNavigasi {
    override val route = "dokter entry"
    override val titleRes = "Entry Dokter"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertDokterView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertDokterViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryDokter.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        InsertDokterBody(
            insertUiState = viewModel.uiState,
            onDokterValueChange = viewModel::updateInsertDokterState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertDokter()
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
fun InsertDokterBody(
    insertUiState: InsertUiState,
    onDokterValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onDokterValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.namaDokter,
            onValueChange = { onValueChange(insertUiEvent.copy(namaDokter = it)) },
            label = { Text("nama Dokter") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idDokter,
            onValueChange = { onValueChange(insertUiEvent.copy(idDokter = it)) },
            label = { Text("ID Dokter") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.perawatan,
            onValueChange = { onValueChange(insertUiEvent.copy(perawatan = it)) },
            label = { Text("Perawatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.kontakDokter,
            onValueChange = { onValueChange(insertUiEvent.copy(kontakDokter = it)) },
            label = { Text("Kontak Dokter") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = "Isi Semua Data Dokter!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}
