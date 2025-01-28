package com.example.projectpam.ui.viewmodel.JenisHewanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectpam.model.JenisHewan
import com.example.projectpam.repository.JenisHewanRepository
import kotlinx.coroutines.launch

class UpdateJenisHewanViewModel(private val jenisHewanRepository: JenisHewanRepository) : ViewModel() {
    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun loadJenisHewan(idJenisHewan: String) {
        viewModelScope.launch {
            try {
                val jenisHewan = jenisHewanRepository.getJenisHewanById(idJenisHewan)
                uiState = UpdateUiState(updateUiEvent = jenisHewan.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateJenisHewanState(updateUiEvent: UpdateUiEvent) {
        uiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    suspend fun updateJenisHewan(idJenisHewan: String) {
        viewModelScope.launch {
            try {
                jenisHewanRepository.updateJenisHewan(idJenisHewan, uiState.updateUiEvent.toJenisHewan())
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
    val idJenisHewan: String = "",
    val namaJenisHewan: String = "",
    val deskripsi: String = "",
)

fun UpdateUiEvent.toJenisHewan(): JenisHewan = JenisHewan(
    idJenisHewan = idJenisHewan,
    namaJenisHewan = namaJenisHewan,
    deskripsi = deskripsi,
)

fun JenisHewan.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idJenisHewan = idJenisHewan,
    namaJenisHewan = namaJenisHewan,
    deskripsi = deskripsi,
)