package com.example.projectpam.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.projectpam.DokterApplications
import com.example.projectpam.ui.viewmodel.HewanViewModel.DetailHewanViewModel
import com.example.projectpam.ui.viewmodel.HewanViewModel.HomeHewanViewModel
import com.example.projectpam.ui.viewmodel.HewanViewModel.InsertHewanViewModel
import com.example.projectpam.ui.viewmodel.HewanViewModel.UpdateHewanViewModel
import com.example.projectpam.ui.viewmodel.DokterViewModel.DetailDokterViewModel
import com.example.projectpam.ui.viewmodel.DokterViewModel.HomeDokterViewModel
import com.example.projectpam.ui.viewmodel.DokterViewModel.InsertDokterViewModel
import com.example.projectpam.ui.viewmodel.DokterViewModel.UpdateDokterViewModel
import com.example.projectpam.ui.viewmodel.JenisHewanViewModel.DetailJenisHewanViewModel
import com.example.projectpam.ui.viewmodel.JenisHewanViewModel.HomeJenisHewanViewModel
import com.example.projectpam.ui.viewmodel.JenisHewanViewModel.InsertJenisHewanViewModel
import com.example.projectpam.ui.viewmodel.JenisHewanViewModel.UpdateJenisHewanViewModel
import com.example.projectpam.ui.viewmodel.PerawatanViewModel.DetailPerawatanViewModel
import com.example.projectpam.ui.viewmodel.PerawatanViewModel.HomePerawatanViewModel
import com.example.projectpam.ui.viewmodel.PerawatanViewModel.InsertPerawatanViewModel
import com.example.projectpam.ui.viewmodel.PerawatanViewModel.UpdatePerawatanViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        // Dokter initializers
        initializer { HomeDokterViewModel(DokterApplications().container.dokterRepository) }
        initializer { InsertDokterViewModel(DokterApplications().container.dokterRepository) }
        initializer { DetailDokterViewModel(DokterApplications().container.dokterRepository) }
        initializer { UpdateDokterViewModel(DokterApplications().container.dokterRepository) }

        // Hewan initializers
        initializer { HomeHewanViewModel(DokterApplications().container.hewanRepository) }
        initializer { InsertHewanViewModel(DokterApplications().container.hewanRepository) }
        initializer { DetailHewanViewModel(DokterApplications().container.hewanRepository) }
        initializer { UpdateHewanViewModel(DokterApplications().container.hewanRepository) }

        // Jenis Hewan initializers
        initializer { HomeJenisHewanViewModel(DokterApplications().container.jenisHewanRepository) }
        initializer { InsertJenisHewanViewModel(DokterApplications().container.jenisHewanRepository) }
        initializer { DetailJenisHewanViewModel(DokterApplications().container.jenisHewanRepository) }
        initializer { UpdateJenisHewanViewModel(DokterApplications().container.jenisHewanRepository) }

        // Perawatan initializers
        initializer { HomePerawatanViewModel(DokterApplications().container.perawatanRepository) }
        initializer { InsertPerawatanViewModel(DokterApplications().container.perawatanRepository) }
        initializer { DetailPerawatanViewModel(DokterApplications().container.perawatanRepository) }
        initializer { UpdatePerawatanViewModel(DokterApplications().container.perawatanRepository) }
    }

    fun CreationExtras.DokterApplications(): DokterApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DokterApplications)
}
