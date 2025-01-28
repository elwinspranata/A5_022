package com.example.projectpam.ui.viewmodel.HewanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectpam.model.Hewan
import com.example.projectpam.repository.HewanRepository
import kotlinx.coroutines.launch

class InsertHewanViewModel(private val hewanRepository: HewanRepository) : ViewModel() {

    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertHewanState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertHewan() {
        viewModelScope.launch {
            try {
                hewanRepository.insertHewan(uiState.insertUiEvent.toHewan())
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
    val idHewan: String = "",
    val namaHewan: String = "",
    val jenisHewanId: String = "",
    val pemilik: String = "",
    val kontakPemilik: String = "",
    val tanggalLahir: String = "",
    val catatanKesehatan: String = "",
)

fun InsertUiEvent.toHewan(): Hewan = Hewan(
    idHewan = idHewan,
    namaHewan = namaHewan,
    jenisHewanId = jenisHewanId,
    pemilik = pemilik,
    kontakPemilik = kontakPemilik,
    tanggalLahir = tanggalLahir,
    catatanKesehatan = catatanKesehatan,
)

fun Hewan.toUiStateHewan(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Hewan.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idHewan = idHewan,
    namaHewan = namaHewan,
    jenisHewanId = jenisHewanId,
    pemilik = pemilik,
    kontakPemilik = kontakPemilik,
    tanggalLahir = tanggalLahir,
    catatanKesehatan = catatanKesehatan,
)