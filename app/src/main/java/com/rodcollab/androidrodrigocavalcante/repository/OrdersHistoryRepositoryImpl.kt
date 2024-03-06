package com.rodcollab.androidrodrigocavalcante.repository

import com.rodcollab.androidrodrigocavalcante.database.OrderHistoryDao
import com.rodcollab.androidrodrigocavalcante.network.MaxApi
import javax.inject.Inject

class OrdersHistoryRepositoryImpl @Inject constructor(
    private val ordersHistoryDao: OrderHistoryDao,
    private val maxApi: MaxApi
) : OrdersHistoryRepository {

}