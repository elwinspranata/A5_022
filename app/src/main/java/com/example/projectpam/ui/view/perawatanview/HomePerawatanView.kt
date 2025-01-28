package com.example.projectpam.ui.view.perawatanview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectpam.model.Perawatan
import com.example.projectpam.ui.costumwidget.CostumeTopAppBar
import com.example.projectpam.ui.navigation.DestinasiNavigasi
import com.example.projectpam.ui.view.dokterview.OnError
import com.example.projectpam.ui.view.dokterview.OnLoading
import com.example.projectpam.ui.viewmodel.PenyediaViewModel
import com.example.projectpam.ui.viewmodel.PerawatanViewModel.HomePerawatanViewModel
import com.example.projectpam.ui.viewmodel.PerawatanViewModel.HomeUiState

object DestinasiHomePerawatan : DestinasiNavigasi {
    override val route = "home perawatan"
    override val titleRes = "Home Perawatan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePerawatanScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomePerawatanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomePerawatan.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPerawatan()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Perawatan")
            }
        },
    ) { innerPadding ->
        HomePerawatanStatus(
            homeUiState = viewModel.perawatanUIState,
            retryAction = { viewModel.getPerawatan() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deletePerawatan(it.idPerawatan)
                viewModel.getPerawatan()
            }
        )
    }
}

@Composable
fun HomePerawatanStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Perawatan) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success -> {
            if (homeUiState.perawatan.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Perawatan")
                }
            } else {
                PerawatanLayout(
                    perawatan = homeUiState.perawatan,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idPerawatan)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        }
        is HomeUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun PerawatanLayout(
    perawatan: List<Perawatan>,
    modifier: Modifier = Modifier,
    onDetailClick: (Perawatan) -> Unit,
    onDeleteClick: (Perawatan) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(perawatan) { perawatanItem ->
            PerawatanCard(
                perawatan = perawatanItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(perawatanItem) },
                onDeleteClick = {
                    onDeleteClick(perawatanItem)
                }
            )
        }
    }
}

@Composable
fun PerawatanCard(
    perawatan: Perawatan,
    modifier: Modifier = Modifier,
    onDeleteClick: (Perawatan) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = perawatan.idPerawatan,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(perawatan) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
            Text(
                text = "Id Hewan: ${perawatan.idHewan}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Id Dokter: ${perawatan.idDokter}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Tanggal: ${perawatan.tanggalPerawatan}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Detail: ${perawatan.detailPerawatan}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
