package com.example.projectpam.ui.viewmodel.DokterViewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectpam.model.Dokter
import com.example.projectpam.repository.DokterRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeUiState {
    data class Success(val dokter: List<Dokter>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeDokterViewModel(private val dokterRepository: DokterRepository) : ViewModel() {

    var dokterUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getDokter()
    }

    fun getDokter() {
        viewModelScope.launch {
            dokterUIState = HomeUiState.Loading
            dokterUIState = try {
                HomeUiState.Success(dokterRepository.getDokter())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deleteDokter(idDokter: String) {
        viewModelScope.launch {
            try {
                dokterRepository.deleteDokter(idDokter)
            } catch (e: IOException) {
                dokterUIState = HomeUiState.Error
            } catch (e: HttpException) {
                dokterUIState = HomeUiState.Error
            }
        }
    }
}
