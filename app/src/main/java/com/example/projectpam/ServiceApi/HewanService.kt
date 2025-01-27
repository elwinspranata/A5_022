package com.example.projectpam.ServiceApi

import com.example.projectpam.model.Hewan
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface HewanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("bacahewan.php")
    suspend fun getHewan(): List<Hewan>

    @GET("baca1hewan.php/{idHewan}")
    suspend fun getHewanById(@Query("idHewan") idHewan: String): Hewan

    @POST("inserthewan.php")
    suspend fun insertHewan(@Body hewan: Hewan)

    @PUT("edithewan.php")
    suspend fun updateHewan(@Query("idHewan") idHewan: String, @Body hewan: Hewan)

    @DELETE("deletehewan.php/{id_hewan}")
    suspend fun deleteHewan(@Query("idHewan") idHewan: String): Response<Void>
}