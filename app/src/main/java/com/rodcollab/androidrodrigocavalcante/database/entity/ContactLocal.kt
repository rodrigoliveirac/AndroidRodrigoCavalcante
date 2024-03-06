package com.rodcollab.androidrodrigocavalcante.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="contact")
data class ContactLocal(
    @PrimaryKey(autoGenerate = true) val uuid: Long = 0L,
    val clientId: String,
    val nome:String,
    val telefone: String,
    val celular:String,
    val conjuge: String,
    val tipo: String,
    val time:String,
    val e_mail: String,
    val data_nascimento: String,
    val dataNascimentoConjuge:String
)