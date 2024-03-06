package com.rodcollab.androidrodrigocavalcante.repository

import com.rodcollab.androidrodrigocavalcante.database.entity.OrderHistoryLocal
import com.rodcollab.androidrodrigocavalcante.model.OrderHistory

interface OrdersHistoryRepository {
    suspend fun getOrdersHistory() : List<OrderHistory>

}
