package com.example.projectpam.ui.viewmodel.HewanViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.projectpam.model.Hewan
import com.example.projectpam.repository.HewanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val hewan: Hewan) : DetailUiState()
    object Error : DetailUiState()
}

class DetailHewanViewModel(private val repository: HewanRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getHewanById(idHewan: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            try {
                val hewan = repository.getHewanById(idHewan)
                if (hewan != null) {
                    _uiState.value = DetailUiState.Success(hewan)
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
            repository: HewanRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DetailHewanViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return DetailHewanViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}