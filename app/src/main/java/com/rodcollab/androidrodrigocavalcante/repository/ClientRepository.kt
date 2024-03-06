package com.rodcollab.androidrodrigocavalcante.repository

import com.rodcollab.androidrodrigocavalcante.database.entity.ClientLocal
import com.rodcollab.androidrodrigocavalcante.model.ClientData

interface ClientRepository {

    suspend fun getClient(): ClientData?
    suspend fun upsertAll(clientData: List<ClientLocal>)
}
