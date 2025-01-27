package com.example.projectpam.model

import kotlinx.serialization.*

@Serializable
data class Perawatan(
    @SerialName("id_perawatan")
    val idPerawatan: String,

    @SerialName("id_hewan")
    val idHewan: String,

    @SerialName("id_dokter")
    val idDokter: String,

    @SerialName("tanggal_perawatan")
    val tanggalPerawatan: String,

    @SerialName("detail_perawatan")
    val detailPerawatan: String
)
