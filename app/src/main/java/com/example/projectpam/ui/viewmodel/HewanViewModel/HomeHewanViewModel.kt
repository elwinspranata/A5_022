package com.example.projectpam.ui.viewmodel.HewanViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectpam.model.Hewan
import com.example.projectpam.repository.HewanRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeUiState {
    data class Success(val hewan: List<Hewan>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeHewanViewModel(private val hewanRepository: HewanRepository) : ViewModel() {

    var hewanUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getHewan()
    }

    fun getHewan() {
        viewModelScope.launch {
            hewanUIState = HomeUiState.Loading
            hewanUIState = try {
                HomeUiState.Success(hewanRepository.getHewan())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deleteHewan(idHewan: String) {
        viewModelScope.launch {
            try {
                hewanRepository.deleteHewan(idHewan)
            } catch (e: IOException) {
                hewanUIState = HomeUiState.Error
            } catch (e: HttpException) {
                hewanUIState = HomeUiState.Error
            }
        }
    }
}