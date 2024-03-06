package com.rodcollab.androidrodrigocavalcante.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="client")
class ClientLocal(
    @PrimaryKey val id: Int,
    val codigo: String,
    val razao_social: String,
    val nomeFantasia: String,
    val cnpj: String,
    val ramo_atividade: String,
    val endereco: String,
    val status: String,
)