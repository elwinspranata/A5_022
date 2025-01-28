package com.example.projectpam.ui.viewmodel.PerawatanViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.projectpam.model.Perawatan
import com.example.projectpam.repository.PerawatanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val perawatan: Perawatan) : DetailUiState()
    object Error : DetailUiState()
}

class DetailPerawatanViewModel(private val repository: PerawatanRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getPerawatanById(idPerawatan: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            try {
                val perawatan = repository.getPerawatanById(idPerawatan)
                if (perawatan != null) {
                    _uiState.value = DetailUiState.Success(perawatan)
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
            repository: PerawatanRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DetailPerawatanViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return DetailPerawatanViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}
