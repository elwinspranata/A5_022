package com.example.projectpam.ui.view.hewanview

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectpam.R
import com.example.projectpam.model.Hewan
import com.example.projectpam.ui.costumwidget.CostumeTopAppBar
import com.example.projectpam.ui.navigation.DestinasiNavigasi
import com.example.projectpam.ui.viewmodel.HewanViewModel.HomeHewanViewModel
import com.example.projectpam.ui.viewmodel.HewanViewModel.HomeUiState
import com.example.projectpam.ui.viewmodel.PenyediaViewModel

object DestinasiHomeHewan : DestinasiNavigasi {
    override val route = "home hewan"
    override val titleRes = "Home Hewan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHewanScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeHewanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeHewan.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getHewan()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Hewan")
            }
        },
    ) { innerPadding ->
        HomeHewanStatus(
            homeUiState = viewModel.hewanUIState,
            retryAction = { viewModel.getHewan() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteHewan(it.idHewan)
                viewModel.getHewan()
            }
        )
    }
}

@Composable
fun HomeHewanStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Hewan) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success -> {
            if (homeUiState.hewan.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Hewan")
                }
            } else {
                HewanLayout(
                    hewan = homeUiState.hewan,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idHewan)
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
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = ""
        )
        Text(text = "Loading failed", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text("Retry")
        }
    }
}

@Composable
fun HewanLayout(
    hewan: List<Hewan>,
    modifier: Modifier = Modifier,
    onDetailClick: (Hewan) -> Unit,
    onDeleteClick: (Hewan) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(hewan) { hewanItem ->
            HewanCard(
                hewan = hewanItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(hewanItem) },
                onDeleteClick = {
                    onDeleteClick(hewanItem)
                }
            )
        }
    }
}

@Composable
fun HewanCard(
    hewan: Hewan,
    modifier: Modifier = Modifier,
    onDeleteClick: (Hewan) -> Unit = {}
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
                    text = hewan.idHewan,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(hewan) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
                Text(
                    text = hewan.namaHewan,
                    style = MaterialTheme.typography.titleMedium
                    )
                }
                Text(
                    text = hewan.jenisHewanId,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Text(
                text = hewan.kontakPemilik,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = hewan.pemilik,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = hewan.tanggalLahir,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = hewan.catatanKesehatan,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
