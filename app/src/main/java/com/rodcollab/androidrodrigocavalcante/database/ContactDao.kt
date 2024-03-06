package com.rodcollab.androidrodrigocavalcante.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.rodcollab.androidrodrigocavalcante.database.entity.ContactLocal

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact WHERE clientId =:id")
    fun getContactsByClientId(id: Int) : List<ContactLocal>

    @Upsert
    fun upsertAll(contacts: List<ContactLocal>)

    @Insert
    fun insert(contactsLocal: ContactLocal)

}
