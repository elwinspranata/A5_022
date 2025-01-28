package com.example.projectpam.repository

import com.example.projectpam.ServiceApi.PerawatanService
import com.example.projectpam.model.Perawatan

interface PerawatanRepository {
    suspend fun getPerawatan(): List<Perawatan>
    suspend fun insertPerawatan(perawatan: Perawatan)
    suspend fun updatePerawatan(idPerawatan: String, perawatan: Perawatan)
    suspend fun deletePerawatan(idPerawatan: String)
    suspend fun getPerawatanById(idPerawatan: String): Perawatan
}

class NetworkPerawatanRepository(private val perawatanService: PerawatanService) : PerawatanRepository {
    override suspend fun getPerawatan(): List<Perawatan> = perawatanService.getPerawatan()

    override suspend fun insertPerawatan(perawatan: Perawatan) {
        perawatanService.insertPerawatan(perawatan)
    }

    override suspend fun updatePerawatan(idPerawatan: String, perawatan: Perawatan) {
        perawatanService.updatePerawatan(idPerawatan, perawatan)
    }

    override suspend fun deletePerawatan(idPerawatan: String) {
        try {
            val response = perawatanService.deletePerawatan(idPerawatan)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete perawatan. HTTP Status Code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPerawatanById(idPerawatan: String): Perawatan {
        return perawatanService.getPerawatanById(idPerawatan)
    }
}
