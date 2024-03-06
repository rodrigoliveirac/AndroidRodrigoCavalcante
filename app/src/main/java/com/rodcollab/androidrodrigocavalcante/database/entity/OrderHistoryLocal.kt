package com.rodcollab.androidrodrigocavalcante.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName="orders")
data class OrderHistoryLocal(
    @PrimaryKey val uuid: String
)