package com.example.projectpam.ui.viewmodel.JenisHewanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectpam.model.JenisHewan
import com.example.projectpam.repository.JenisHewanRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeUiState {
    data class Success(val jenisHewan: List<JenisHewan>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeJenisHewanViewModel(private val jenisHewanRepository: JenisHewanRepository) : ViewModel() {

    var jenisHewanUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getJenisHewan()
    }

    fun getJenisHewan() {
        viewModelScope.launch {
            jenisHewanUIState = HomeUiState.Loading
            jenisHewanUIState = try {
                HomeUiState.Success(jenisHewanRepository.getJenisHewan())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deleteJenisHewan(idJenisHewan: String) {
        viewModelScope.launch {
            try {
                jenisHewanRepository.deleteJenisHewan(idJenisHewan)
            } catch (e: IOException) {
                jenisHewanUIState = HomeUiState.Error
            } catch (e: HttpException) {
                jenisHewanUIState = HomeUiState.Error
            }
        }
    }
}
