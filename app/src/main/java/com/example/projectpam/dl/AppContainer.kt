package com.example.projectpam.dl

import com.example.projectpam.ServiceApi.DokterService
import com.example.projectpam.ServiceApi.HewanService
import com.example.projectpam.ServiceApi.JenisHewanService
import com.example.projectpam.ServiceApi.PerawatanService
import com.example.projectpam.repository.DokterRepository
import com.example.projectpam.repository.HewanRepository
import com.example.projectpam.repository.JenisHewanRepository
import com.example.projectpam.repository.PerawatanRepository
import com.example.projectpam.repository.NetworkDokterRepository
import com.example.projectpam.repository.NetworkHewanRepository
import com.example.projectpam.repository.NetworkJenisHewanRepository
import com.example.projectpam.repository.NetworkPerawatanRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val dokterRepository: DokterRepository
    val hewanRepository: HewanRepository
    val jenisHewanRepository: JenisHewanRepository
    val perawatanRepository: PerawatanRepository // Added PerawatanRepository
}

class AppContainerImpl : AppContainer {
    private val baseUrl = "http://10.0.2.2:80/klinikhewan/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val dokterService: DokterService by lazy {
        retrofit.create(DokterService::class.java)
    }

    private val hewanService: HewanService by lazy {
        retrofit.create(HewanService::class.java)
    }

    private val jenisHewanService: JenisHewanService by lazy {
        retrofit.create(JenisHewanService::class.java)
    }

    private val perawatanService: PerawatanService by lazy { // Added PerawatanService
        retrofit.create(PerawatanService::class.java)
    }

    override val dokterRepository: DokterRepository by lazy {
        NetworkDokterRepository(dokterService)
    }

    override val hewanRepository: HewanRepository by lazy {
        NetworkHewanRepository(hewanService)
    }

    override val jenisHewanRepository: JenisHewanRepository by lazy {
        NetworkJenisHewanRepository(jenisHewanService)
    }

    override val perawatanRepository: PerawatanRepository by lazy { // Added PerawatanRepository
        NetworkPerawatanRepository(perawatanService)
    }
}
