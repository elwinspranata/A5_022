package com.example.projectpam.ui.viewmodel.HewanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectpam.model.Hewan
import com.example.projectpam.repository.HewanRepository
import kotlinx.coroutines.launch

class UpdateHewanViewModel(private val hewanRepository: HewanRepository) : ViewModel() {
    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun loadHewan(idHewan: String) {
        viewModelScope.launch {
            try {
                val hewan = hewanRepository.getHewanById(idHewan)
                uiState = UpdateUiState(updateUiEvent = hewan.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateHewanState(updateUiEvent: UpdateUiEvent) {
        uiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    suspend fun updateHewan(idHewan: String) {
        viewModelScope.launch {
            try {
                hewanRepository.updateHewan(idHewan, uiState.updateUiEvent.toHewan())
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
    val idHewan: String = "",
    val namaHewan: String = "",
    val jenisHewanId: String = "",
    val pemilik: String = "",
    val kontakPemilik: String = "",
    val tanggalLahir: String = "",
    val catatanKesehatan: String = ""
)

fun UpdateUiEvent.toHewan(): Hewan = Hewan(
    idHewan = idHewan,
    namaHewan = namaHewan,
    jenisHewanId = jenisHewanId,
    pemilik = pemilik,
    kontakPemilik = kontakPemilik,
    tanggalLahir = tanggalLahir,
    catatanKesehatan = catatanKesehatan,
)

fun Hewan.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idHewan = idHewan,
    namaHewan = namaHewan,
    jenisHewanId = jenisHewanId,
    pemilik = pemilik,
    kontakPemilik = kontakPemilik,
    tanggalLahir = tanggalLahir,
    catatanKesehatan = catatanKesehatan,
)
