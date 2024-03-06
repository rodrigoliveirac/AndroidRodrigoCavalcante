package com.rodcollab.androidrodrigocavalcante.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.rodcollab.androidrodrigocavalcante.database.entity.ClientLocal

@Dao
interface ClientDao {

    @Query("SELECT * FROM client")
    suspend fun getClients() : List<ClientLocal>

    @Upsert
    suspend fun upsertAll(clients: List<ClientLocal>)

}
