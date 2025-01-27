package com.example.projectpam.ServiceApi
import com.example.projectpam.model.Dokter
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface DokterService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("bacadokter.php")
    suspend fun getDokter(): List<Dokter>

    @GET("baca1dokter.php/{idDokter}")
    suspend fun getDokterById(@Query("idDokter") idDokter: String): Dokter

    @POST("insertdokter.php")
    suspend fun insertDokter(@Body dokter: Dokter)

    @PUT("editdokter.php")
    suspend fun updateDokter(@Query("idDokter") idDokter: String, @Body dokter: Dokter)

    @DELETE("deletedokter.php/{id_dokter}")
    suspend fun deleteDokter(@Query("idDokter") idDokter: String): Response<Void>
}
