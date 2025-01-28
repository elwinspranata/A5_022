package com.example.projectpam.ui.viewmodel.DokterViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectpam.model.Dokter
import com.example.projectpam.repository.DokterRepository
import kotlinx.coroutines.launch

class UpdateDokterViewModel(private val dokterRepository: DokterRepository) : ViewModel() {
    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun loadDokter(idDokter: String) {
        viewModelScope.launch {
            try {
                val dokter = dokterRepository.getDokterById(idDokter)
                uiState = UpdateUiState(updateUiEvent = dokter.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateDokterState(updateUiEvent: UpdateUiEvent) {
        uiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    suspend fun updateDokter(idDokter: String) {
        viewModelScope.launch {
            try {
                dokterRepository.updateDokter(idDokter, uiState.updateUiEvent.toDokter())
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
    val idDokter: String = "",
    val namaDokter: String = "",
    val perawatan: String = "",
    val kontakDokter: String = ""
)

fun UpdateUiEvent.toDokter(): Dokter = Dokter(
    idDokter = idDokter,
    namaDokter = namaDokter,
    perawatan = perawatan,
    kontakDokter = kontakDokter
)

fun Dokter.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idDokter = idDokter,
    namaDokter = namaDokter,
    perawatan = perawatan,
    kontakDokter = kontakDokter
)
