package com.rodcollab.androidrodrigocavalcante.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName="orders")
data class OrderHistoryLocal(
    @PrimaryKey(autoGenerate = true) val uuid: Long = 0L,
    val numero_ped_Rca: Long,
    val numero_ped_erp: String,
    val codigoCliente: String,
    val NOMECLIENTE: String,
    val data: String,
    val status: String,
    val critica: String,
    val tipo: String,
    val legendas: ArrayList<String>?
)