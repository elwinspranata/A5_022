package com.example.projectpam.ui.viewmodel.PerawatanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectpam.model.Perawatan
import com.example.projectpam.repository.PerawatanRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeUiState {
    data class Success(val perawatan: List<Perawatan>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomePerawatanViewModel(private val perawatanRepository: PerawatanRepository) : ViewModel() {

    var perawatanUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getPerawatan()
    }

    fun getPerawatan() {
        viewModelScope.launch {
            perawatanUIState = HomeUiState.Loading
            perawatanUIState = try {
                HomeUiState.Success(perawatanRepository.getPerawatan())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deletePerawatan(idPerawatan: String) {
        viewModelScope.launch {
            try {
                perawatanRepository.deletePerawatan(idPerawatan)
            } catch (e: IOException) {
                perawatanUIState = HomeUiState.Error
            } catch (e: HttpException) {
                perawatanUIState = HomeUiState.Error
            }
        }
    }
}
