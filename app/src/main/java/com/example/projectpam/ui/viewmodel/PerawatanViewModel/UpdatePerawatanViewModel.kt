package com.example.projectpam.ui.viewmodel.PerawatanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectpam.model.Perawatan
import com.example.projectpam.repository.PerawatanRepository
import kotlinx.coroutines.launch

class UpdatePerawatanViewModel(private val perawatanRepository: PerawatanRepository) : ViewModel() {
    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun loadPerawatan(idPerawatan: String) {
        viewModelScope.launch {
            try {
                val perawatan = perawatanRepository.getPerawatanById(idPerawatan)
                uiState = UpdateUiState(updateUiEvent = perawatan.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updatePerawatanState(updateUiEvent: UpdateUiEvent) {
        uiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    suspend fun updatePerawatan(idPerawatan: String) {
        viewModelScope.launch {
            try {
                perawatanRepository.updatePerawatan(idPerawatan, uiState.updateUiEvent.toPerawatan())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class UpdateUiState(
    val updateUiEvent: UpdateUiEvent = UpdateUiEvent()
)

data class UpdateUiEvent(
    val idPerawatan: String = "",
    val idHewan: String = "",
    val idDokter: String = "",
    val tanggalPerawatan: String = "",
    val detailPerawatan: String = ""
)

fun UpdateUiEvent.toPerawatan(): Perawatan = Perawatan(
    idPerawatan = idPerawatan,
    idHewan = idHewan,
    idDokter = idDokter,
    tanggalPerawatan = tanggalPerawatan,
    detailPerawatan = detailPerawatan
)

fun Perawatan.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idPerawatan = idPerawatan,
    idHewan = idHewan,
    idDokter = idDokter,
    tanggalPerawatan = tanggalPerawatan,
    detailPerawatan = detailPerawatan
)
