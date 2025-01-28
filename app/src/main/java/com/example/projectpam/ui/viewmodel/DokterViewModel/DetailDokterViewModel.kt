package com.example.projectpam.ui.viewmodel.DokterViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.projectpam.model.Dokter
import com.example.projectpam.repository.DokterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val dokter: Dokter) : DetailUiState()
    object Error : DetailUiState()
}

class DetailDokterViewModel(private val repository: DokterRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getDokterById(idDokter: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            try {
                val dokter = repository.getDokterById(idDokter)
                if (dokter != null) {
                    _uiState.value = DetailUiState.Success(dokter)
                } else {
                    _uiState.value = DetailUiState.Error
                }
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error
            }
        }
    }

    companion object {
        fun provideFactory(
            repository: DokterRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DetailDokterViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return DetailDokterViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}
