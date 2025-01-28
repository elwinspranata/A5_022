package com.example.projectpam.ui.view.jenishewanview

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
import com.example.projectpam.model.JenisHewan
import com.example.projectpam.ui.costumwidget.CostumeTopAppBar
import com.example.projectpam.ui.navigation.DestinasiNavigasi
import com.example.projectpam.ui.viewmodel.JenisHewanViewModel.HomeJenisHewanViewModel
import com.example.projectpam.ui.viewmodel.JenisHewanViewModel.HomeUiState
import com.example.projectpam.ui.viewmodel.PenyediaViewModel

object DestinasiHomeJenisHewan : DestinasiNavigasi {
    override val route = "home jenis hewan"
    override val titleRes = "Home Jenis Hewan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeJenisHewanScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeJenisHewanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeJenisHewan.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getJenisHewan()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Jenis Hewan")
            }
        },
    ) { innerPadding ->
        HomeJenisHewanStatus(
            homeUiState = viewModel.jenisHewanUIState,
            retryAction = { viewModel.getJenisHewan() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteJenisHewan(it.idJenisHewan)
                viewModel.getJenisHewan()
            }
        )
    }
}

@Composable
fun HomeJenisHewanStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (JenisHewan) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (homeUiState) {
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success -> {
            if (homeUiState.jenisHewan.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Jenis Hewan")
                }
            } else {
                JenisHewanLayout(
                    jenisHewan = homeUiState.jenisHewan,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idJenisHewan)
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
fun JenisHewanLayout(
    jenisHewan: List<JenisHewan>,
    modifier: Modifier = Modifier,
    onDetailClick: (JenisHewan) -> Unit,
    onDeleteClick: (JenisHewan) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(jenisHewan) { jenisHewanItem ->
            JenisHewanCard(
                jenisHewan = jenisHewanItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(jenisHewanItem) },
                onDeleteClick = {
                    onDeleteClick(jenisHewanItem)
                }
            )
        }
    }
}

@Composable
fun JenisHewanCard(
    jenisHewan: JenisHewan,
    modifier: Modifier = Modifier,
    onDeleteClick: (JenisHewan) -> Unit = {}
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
                    text = jenisHewan.idJenisHewan,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(jenisHewan) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
            Text(
                text = jenisHewan.namaJenisHewan,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = jenisHewan.deskripsi,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
