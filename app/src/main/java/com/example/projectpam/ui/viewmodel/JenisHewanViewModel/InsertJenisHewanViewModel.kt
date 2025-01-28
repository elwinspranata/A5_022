package com.example.projectpam.ui.viewmodel.JenisHewanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectpam.model.JenisHewan
import com.example.projectpam.repository.JenisHewanRepository
import kotlinx.coroutines.launch

class InsertJenisHewanViewModel(private val jenisHewanRepository: JenisHewanRepository) : ViewModel() {

    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertJenisHewanState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertJenisHewan() {
        viewModelScope.launch {
            try {
                jenisHewanRepository.insertJenisHewan(uiState.insertUiEvent.toJenisHewan())
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
    val idJenisHewan: String = "",
    val namaJenisHewan: String = "",
    val deskripsi: String = "",
)

fun InsertUiEvent.toJenisHewan(): JenisHewan = JenisHewan(
    idJenisHewan = idJenisHewan,
    namaJenisHewan = namaJenisHewan,
    deskripsi = deskripsi,
)

fun JenisHewan.toUiStateJenisHewan(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun JenisHewan.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idJenisHewan = idJenisHewan,
    namaJenisHewan = namaJenisHewan,
    deskripsi = deskripsi,
)