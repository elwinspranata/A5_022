package com.example.projectpam.model

import kotlinx.serialization.*


@Serializable
data class JenisHewan (
    @SerialName("id_jenis_hewan")
    val idJenisHewan: String,

    @SerialName("nama_jenis_hewan")
    val namaJenisHewan: String,

    @SerialName("deskripsi")
    val deskripsi: String
)
