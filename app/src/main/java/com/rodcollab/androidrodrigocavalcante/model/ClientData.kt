package com.rodcollab.androidrodrigocavalcante.model

data class ClientData(
    val id: Int,
    val codigo: String,
    val razao_social: String,
    val nomeFantasia: String,
    val cnpj: String,
    val ramo_atividade: String,
    val endereco: String,
    val status: String,
    val contatos: ArrayList<Contact>
)