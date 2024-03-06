package com.rodcollab.androidrodrigocavalcante.repository

import com.rodcollab.androidrodrigocavalcante.database.OrderHistoryDao
import com.rodcollab.androidrodrigocavalcante.database.entity.OrderHistoryLocal
import com.rodcollab.androidrodrigocavalcante.model.OrderHistory
import com.rodcollab.androidrodrigocavalcante.network.MaxApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrdersHistoryRepositoryImpl @Inject constructor(
    private val ordersHistoryDao: OrderHistoryDao,
    private val maxApi: MaxApi
) : OrdersHistoryRepository {


    override suspend fun getOrdersHistory(): List<OrderHistory> {
        return withContext(Dispatchers.IO) {
            var orders: List<OrderHistory> = mutableListOf()
            val ordersFromDb = async {
                ordersHistoryDao.getOrdersHistory().map { orderLocal ->
                    OrderHistory(
                        numero_ped_Rca = orderLocal.numero_ped_Rca,
                        numero_ped_erp = orderLocal.numero_ped_erp,
                        codigoCliente = orderLocal.codigoCliente,
                        NOMECLIENTE = orderLocal.NOMECLIENTE,
                        data = orderLocal.data,
                        status = orderLocal.status,
                        critica = orderLocal.critica,
                        tipo = orderLocal.tipo,
                        legendas = orderLocal.legendas
                    )
                }
            }.await()

            if(ordersFromDb.isNotEmpty()) {
                orders = ordersFromDb
            } else {
                try {
                    val data = maxApi.getOrdersHistory()
                    if(data.isSuccessful) {
                        data.body()?.pedidos?.let { arrayOrder ->
                            orders = arrayOrder.toList()

                           val orderHistoryLocal = async {
                               arrayOrder.toList().map { order ->

                                   OrderHistoryLocal(
                                       numero_ped_Rca = order.numero_ped_Rca ?: 0L,
                                       numero_ped_erp = order.numero_ped_erp ?: "",
                                       codigoCliente = order.codigoCliente ?: "",
                                       NOMECLIENTE = order.NOMECLIENTE ?: "",
                                       data = order.data ?: "",
                                       status = order.status ?: "",
                                       critica = order.critica ?: "",
                                       tipo = order.tipo ?: "",
                                       legendas = order.legendas ?: arrayListOf()
                                   )
                               }
                           }.await()
                            ordersHistoryDao.insertAll(orderHistoryLocal)
                        }
                    }
                } catch (_:Exception) {

                }
            }
            orders
        }
    }

}