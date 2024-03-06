package com.rodcollab.androidrodrigocavalcante.repository

import android.util.Log
import com.rodcollab.androidrodrigocavalcante.database.ClientDao
import com.rodcollab.androidrodrigocavalcante.database.ContactDao
import com.rodcollab.androidrodrigocavalcante.database.entity.ClientLocal
import com.rodcollab.androidrodrigocavalcante.database.entity.ContactLocal
import com.rodcollab.androidrodrigocavalcante.model.ClientData
import com.rodcollab.androidrodrigocavalcante.model.Contact
import com.rodcollab.androidrodrigocavalcante.network.MaxApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClientRepositoryImpl @Inject constructor(
    private val clientDao:ClientDao,
    private val contactDao: ContactDao,
    private val maxApi: MaxApi) : ClientRepository {


    override suspend fun getClient(): ClientData? {

        var client: ClientData? = null

        withContext(Dispatchers.IO) {
            val clients = async {
                clientDao.getClients().map {
                    val contacts = async {
                        contactDao.getContactsByClientId(it.id).map {
                            Contact(
                                nome = it.nome,
                                telefone = it.telefone,
                                celular = it.celular,
                                conjuge = it.conjuge,
                                tipo = it.tipo,
                                time = it.time,
                                e_mail = it.e_mail,
                                data_nascimento = it.data_nascimento,
                                dataNascimentoConjuge = it.dataNascimentoConjuge
                            )
                        }
                    }.await()

                    ClientData(
                        id = it.id,
                        codigo = it.codigo,
                        razao_social = it.razao_social,
                        nomeFantasia  = it.nomeFantasia,
                        cnpj = it.cnpj,
                        ramo_atividade = it.ramo_atividade,
                        endereco = it.endereco,
                        status = it.status,
                        contatos = ArrayList(contacts)
                    )
                }
            }.await()

            if(clients.isNotEmpty()) {
                client = clients.first()
            } else {
                try {
                    val data = maxApi.clientData()
                    if(data?.isSuccessful == true){
                        client = data.body()?.cliente
                        client?.let { client ->
                            val clientLocal = ClientLocal(
                                id = client.id,
                                codigo = client.codigo,
                                razao_social =  client.razao_social,
                                nomeFantasia = client.nomeFantasia,
                                cnpj = client.cnpj,
                                ramo_atividade = client.ramo_atividade,
                                endereco = client.endereco,
                                status = client.status,
                            )
                            upsertAll(listOf(clientLocal))

                            val contactsLocal = async {
                                client.contatos.map {
                                    ContactLocal(
                                        clientId = client.id.toString(),
                                        nome = it.nome,
                                        telefone = it.telefone ?: "",
                                        celular = it.celular ?: "",
                                        conjuge = it.conjuge ?: "",
                                        tipo = it.tipo ?: "",
                                        time = it.time ?: "",
                                        e_mail = it.e_mail ?: "",
                                        data_nascimento = it.data_nascimento ?: "",
                                        dataNascimentoConjuge = it.dataNascimentoConjuge ?: ""
                                    )
                                }
                            }.await()
                            contactsLocal.forEach { contactsLocal ->
                                contactDao.insert(contactsLocal)
                            }
                        }
                    }
                } catch (_:Exception) {

                }
            }
        }
        return client
    }

    override suspend fun upsertAll(clientData: List<ClientLocal>) {
        clientDao.upsertAll(clientData)
    }


}