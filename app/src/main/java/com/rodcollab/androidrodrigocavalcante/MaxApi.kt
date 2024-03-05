package com.rodcollab.androidrodrigocavalcante

import retrofit2.Response
import retrofit2.http.GET

interface MaxApi {
    @GET("android/cliente")
    suspend fun clientData() : Response<ResponseData>?
}

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

data class Contact(
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
data class ResponseData(
    val cliente: ClientData
)

