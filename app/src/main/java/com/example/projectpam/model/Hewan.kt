package com.example.projectpam.model


import kotlinx.serialization.*


@Serializable
data class Hewan (
    @SerialName("id_hewan")
    val idHewan: String,

    @SerialName("nama_hewan")
    val namaHewan: String,

    @SerialName("jenis_hewan_id")
    val jenisHewanId: String,

    @SerialName("pemilik")
    val pemilik: String,

    @SerialName("kontak_pemilik")
    val kontakPemilik: String,

    @SerialName("tanggal_lahir")
    val tanggalLahir: String,

    @SerialName("catatan_kesehatan")
    val catatanKesehatan: String
)
