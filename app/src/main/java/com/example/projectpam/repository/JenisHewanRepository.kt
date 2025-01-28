package com.example.projectpam.repository

import com.example.projectpam.ServiceApi.JenisHewanService
import com.example.projectpam.model.JenisHewan

interface JenisHewanRepository {
    suspend fun getJenisHewan(): List<JenisHewan>
    suspend fun insertJenisHewan(jenisHewan: JenisHewan)
    suspend fun updateJenisHewan(idJenisHewan: String, jenisHewan: JenisHewan)
    suspend fun deleteJenisHewan(idJenisHewan: String)
    suspend fun getJenisHewanById(idJenisHewan: String): JenisHewan
}

class NetworkJenisHewanRepository(private val jenisHewanService: JenisHewanService) : JenisHewanRepository {
    override suspend fun getJenisHewan(): List<JenisHewan> = jenisHewanService.getJenisHewan()

    override suspend fun insertJenisHewan(jenisHewan: JenisHewan) {
        jenisHewanService.insertJenisHewan(jenisHewan)
    }

    override suspend fun updateJenisHewan(idJenisHewan: String, jenisHewan: JenisHewan) {
        jenisHewanService.updateJenisHewan(idJenisHewan, jenisHewan)
    }

    override suspend fun deleteJenisHewan(idJenisHewan: String) {
        try {
            val response = jenisHewanService.deleteJenisHewan(idJenisHewan)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete jenis hewan. HTTP Status Code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getJenisHewanById(idJenisHewan: String): JenisHewan {
        return jenisHewanService.getJenisHewanById(idJenisHewan)
    }
}
