package com.example.projectpam.ui.view.perawatanview


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectpam.model.Perawatan
import com.example.projectpam.ui.costumwidget.CostumeTopAppBar
import com.example.projectpam.ui.navigation.DestinasiNavigasi
import com.example.projectpam.ui.view.dokterview.OnError
import com.example.projectpam.ui.view.dokterview.OnLoading
import com.example.projectpam.ui.viewmodel.PenyediaViewModel
import com.example.projectpam.ui.viewmodel.PerawatanViewModel.DetailPerawatanViewModel
import com.example.projectpam.ui.viewmodel.PerawatanViewModel.DetailUiState

object DestinasiDetailPerawatan : DestinasiNavigasi {
    override val route = "detail perawatan"
    override val titleRes = "Detail Perawatan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPerawatanView(
    idPerawatan: String,
    navigateBack: () -> Unit,
    onClick: () -> Unit,
    viewModel: DetailPerawatanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    viewModel.getPerawatanById(idPerawatan)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailPerawatan.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPerawatanById(idPerawatan)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Perawatan")
            }
        },
    ) { innerPadding ->
        val uiState = viewModel.uiState.collectAsState().value
        DetailBody(
            uiState = uiState,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onDeleteClick = {
                viewModel.getPerawatanById(idPerawatan)
                navigateBack()
            }
        )
    }
}

@Composable
fun DetailBody(
    uiState: DetailUiState,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {
    var deleteConfirmationRequired by remember { mutableStateOf(false) }

    when (uiState) {
        is DetailUiState.Loading -> OnLoading(modifier = modifier)
        is DetailUiState.Success -> Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            ItemDetailPerawatan(perawatan = uiState.perawatan)
            Button(
                onClick = { deleteConfirmationRequired = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Delete")
            }
        }
        is DetailUiState.Error -> OnError(retryAction = {}, modifier = modifier)
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

@Composable
fun ItemDetailPerawatan(
    modifier: Modifier = Modifier,
    perawatan: Perawatan
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ComponentDetailPerawatan(judul = "Id Perawatan", isinya = perawatan.idPerawatan)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailPerawatan(judul = "Id Hewan", isinya = perawatan.idHewan)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailPerawatan(judul = "Id Dokter", isinya = perawatan.idDokter)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailPerawatan(judul = "Tanggal Perawatan", isinya = perawatan.tanggalPerawatan)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailPerawatan(judul = "Detail Perawatan", isinya = perawatan.detailPerawatan)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ComponentDetailPerawatan(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp
            )
        )
        Text(
            text = isinya,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
            )
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}
