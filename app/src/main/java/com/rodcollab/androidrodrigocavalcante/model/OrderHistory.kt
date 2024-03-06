package com.rodcollab.androidrodrigocavalcante.model

data class OrderHistory(
    val numero_ped_Rca: Long,
    val numero_ped_erp: String,
    val codigoCliente: String,
    val NOMECLIENTE: String,
    val data: String,
    val status: String,
    val critica: String,
    val tipo: String,
    val legendas: ArrayList<String>
)