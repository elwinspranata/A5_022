package com.example.projectpam.ui.viewmodel.PerawatanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectpam.model.Perawatan
import com.example.projectpam.repository.PerawatanRepository
import kotlinx.coroutines.launch

class InsertPerawatanViewModel(private val perawatanRepository: PerawatanRepository) : ViewModel() {

    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertPerawatanState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertPerawatan() {
        viewModelScope.launch {
            try {
                perawatanRepository.insertPerawatan(uiState.insertUiEvent.toPerawatan())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val idPerawatan: String = "",
    val idHewan: String = "",
    val idDokter: String = "",
    val tanggalPerawatan: String = "",
    val detailPerawatan: String = ""
)

fun InsertUiEvent.toPerawatan(): Perawatan = Perawatan(
    idPerawatan = idPerawatan,
    idHewan = idHewan,
    idDokter = idDokter,
    tanggalPerawatan = tanggalPerawatan,
    detailPerawatan = detailPerawatan
)

fun Perawatan.toUiStatePerawatan(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Perawatan.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idPerawatan = idPerawatan,
    idHewan = idHewan,
    idDokter = idDokter,
    tanggalPerawatan = tanggalPerawatan,
    detailPerawatan = detailPerawatan
)
