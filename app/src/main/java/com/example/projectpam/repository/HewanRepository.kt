package com.example.projectpam.repository

import com.example.projectpam.ServiceApi.HewanService
import com.example.projectpam.model.Hewan

interface HewanRepository {
    suspend fun getHewan(): List<Hewan>
    suspend fun insertHewan(hewan: Hewan)
    suspend fun updateHewan(idHewan: String, hewan: Hewan)
    suspend fun deleteHewan(idHewan: String)
    suspend fun getHewanById(idHewan: String): Hewan
}

class NetworkHewanRepository(private val hewanService: HewanService) : HewanRepository {
    override suspend fun getHewan(): List<Hewan> = hewanService.getHewan()

    override suspend fun insertHewan(hewan: Hewan) {
        hewanService.insertHewan(hewan)
    }

    override suspend fun updateHewan(idHewan: String, hewan: Hewan) {
        hewanService.updateHewan(idHewan, hewan)
    }

    override suspend fun deleteHewan(idHewan: String) {
        try {
            val response = hewanService.deleteHewan(idHewan)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete hewan. HTTP Status Code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getHewanById(idHewan: String): Hewan {
        return hewanService.getHewanById(idHewan)
    }
}