package com.example.projectpam.ServiceApi

import com.example.projectpam.model.Perawatan
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PerawatanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("bacaperawatan.php")
    suspend fun getPerawatan(): List<Perawatan>

    @GET("baca1perawatan.php/{idPerawatan}")
    suspend fun getPerawatanById(@Query("idPerawatan") idPerawatan: String): Perawatan

    @POST("insertperawatan.php")
    suspend fun insertPerawatan(@Body perawatan: Perawatan)

    @PUT("editperawatan.php")
    suspend fun updatePerawatan(@Query("idPerawatan") idPerawatan: String, @Body perawatan: Perawatan)

    @DELETE("deleteperawatan.php/{id_perawatan}")
    suspend fun deletePerawatan(@Query("idPerawatan") idPerawatan: String): Response<Void>
}
