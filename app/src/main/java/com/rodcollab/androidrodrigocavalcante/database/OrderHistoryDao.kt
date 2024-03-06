package com.rodcollab.androidrodrigocavalcante.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.rodcollab.androidrodrigocavalcante.database.entity.OrderHistoryLocal

@Dao
interface OrderHistoryDao {

    @Query("SELECT * FROM orders")
    fun getOrdersHistory(): List<OrderHistoryLocal>
    @Upsert
    fun insertAll(orderHistoryLocal: List<OrderHistoryLocal>)

}
