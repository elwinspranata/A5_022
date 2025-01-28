package com.example.projectpam.ui.viewmodel.JenisHewanViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.projectpam.model.JenisHewan
import com.example.projectpam.repository.JenisHewanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val jenisHewan: JenisHewan) : DetailUiState()
    object Error : DetailUiState()
}

class DetailJenisHewanViewModel(private val repository: JenisHewanRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getJenisHewanById(idJenisHewan: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            try {
                val jenisHewan = repository.getJenisHewanById(idJenisHewan)
                if (jenisHewan != null) {
                    _uiState.value = DetailUiState.Success(jenisHewan)
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
            repository: JenisHewanRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DetailJenisHewanViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return DetailJenisHewanViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}