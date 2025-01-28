package com.example.projectpam.ui.viewmodel.DokterViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectpam.model.Dokter
import com.example.projectpam.repository.DokterRepository
import kotlinx.coroutines.launch

class InsertDokterViewModel(private val dokterRepository: DokterRepository) : ViewModel() {

    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertDokterState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertDokter() {
        viewModelScope.launch {
            try {
                dokterRepository.insertDokter(uiState.insertUiEvent.toDokter())
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
    val idDokter: String = "",
    val namaDokter: String = "",
    val perawatan: String = "",
    val kontakDokter: String = "",
)

fun InsertUiEvent.toDokter(): Dokter = Dokter(
    idDokter = idDokter,
    namaDokter = namaDokter,
    perawatan = perawatan,
    kontakDokter = kontakDokter
)

fun Dokter.toUiStateDokter(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Dokter.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idDokter = idDokter,
    namaDokter = namaDokter,
    perawatan = perawatan,
    kontakDokter = kontakDokter
)
