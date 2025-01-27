package com.example.projectpam.repository

import com.example.projectpam.ServiceApi.DokterService
import com.example.projectpam.model.Dokter

interface DokterRepository {
    suspend fun getDokter(): List<Dokter>
    suspend fun insertDokter(dokter: Dokter)
    suspend fun updateDokter(idDokter: String, dokter: Dokter)
    suspend fun deleteDokter(idDokter: String)
    suspend fun getDokterById(idDokter: String): Dokter
}

class NetworkDokterRepository(private val dokterService: DokterService) : DokterRepository {
    override suspend fun getDokter(): List<Dokter> = dokterService.getDokter()

    override suspend fun insertDokter(dokter: Dokter) {
        dokterService.insertDokter(dokter)
    }

    override suspend fun updateDokter(idDokter: String, dokter: Dokter) {
        dokterService.updateDokter(idDokter, dokter)
    }

    override suspend fun deleteDokter(idDokter: String) {
        try {
            val response = dokterService.deleteDokter(idDokter)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete dokter. HTTP Status Code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }







    override suspend fun getDokterById(idDokter: String): Dokter {
        return dokterService.getDokterById(idDokter)
    }
}
