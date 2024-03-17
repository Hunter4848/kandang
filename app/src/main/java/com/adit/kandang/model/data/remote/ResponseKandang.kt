package com.adit.kandang.model.data.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseKandang(
    @SerializedName("nama_kandang")
    val nama_kandang:String,
    @SerializedName("alamat_kandang")
    val alamat_kandang:String,
    @SerializedName("status_aktif")
    val status_aktif: Boolean,
    @SerializedName("client_name")
    val client_name: String?=null,
    @SerializedName("jenis_peternak")
    val jenis_peternak: String,
    @SerializedName("periode")
    val periode: String,

    @SerializedName("umur")
    val umur: String?,
    @SerializedName("populasi")
    val populasi: String?,
    @SerializedName("body_weight")
    val body_weight: String?,
    @SerializedName("jenis_kandang")
    val jenis_kandang: String?,
    @SerializedName("tanggal_doc")
    val tanggal_doc: String?,

): Serializable {
    private val umurString:String
        get() = umur ?: ""

    private val bodyWeight: String
        get() = body_weight ?: ""

    private val populasiStr: String
        get() = populasi ?: ""

    val typePen: String
        get() = jenis_kandang ?: ""

    val dateDoc: String
        get() = tanggal_doc ?: ""

    fun ageString(): String{
        return "Umur $umurString"
    }
    fun ageSubString(): String{
        return umurString.substringBefore(' ')
    }
    fun populationString(): String{
        return populasiStr.substringBefore(' ')
    }

    fun bodyWeightString(): String{
        return bodyWeight.substringBefore(' ')
    }

    fun getStatusBgColor(): String = when (jenis_peternak) {
        "MANDIRI" ->   if (status_aktif)  "#85C226" else "#555555"
        else -> "#1B6858"
    }
}
