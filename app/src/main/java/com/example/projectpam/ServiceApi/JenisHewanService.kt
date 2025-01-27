package com.example.projectpam.ServiceApi

import com.example.projectpam.model.JenisHewan
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface JenisHewanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("bacajenishewan.php")
    suspend fun getJenisHewan(): List<JenisHewan>

    @GET("baca1jenishewan.php/{idJenisHewan}")
    suspend fun getJenisHewanById(@Query("idJenisHewan") idJenisHewan: String): JenisHewan

    @POST("insertjenishewan.php")
    suspend fun insertJenisHewan(@Body jenisHewan: JenisHewan)

    @PUT("editjenishewan.php")
    suspend fun updateJenisHewan(@Query("idJenisHewan") idJenisHewan: String, @Body jenisHewan: JenisHewan)

    @DELETE("deletejenishewan.php/{id_jenis_hewan}")
    suspend fun deleteJenisHewan(@Query("idJenisHewan") idJenisHewan: String): Response<Void>
}
