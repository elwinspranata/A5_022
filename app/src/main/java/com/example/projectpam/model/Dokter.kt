package com.example.projectpam.model

import kotlinx.serialization.*

@Serializable
data class Dokter (
    @SerialName("id_dokter")
    val idDokter: String,

    @SerialName("nama_dokter")
    val namaDokter: String,
    @SerialName("perawatan")
    val perawatan: String,
    @SerialName("kontakdokter")
    val kontakDokter: String
)
