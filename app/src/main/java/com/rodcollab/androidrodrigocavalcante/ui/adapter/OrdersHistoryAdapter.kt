package com.rodcollab.androidrodrigocavalcante.ui.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodcollab.androidrodrigocavalcante.R
import com.rodcollab.androidrodrigocavalcante.databinding.OrderItemBinding
import com.rodcollab.androidrodrigocavalcante.model.OrderHistory
import com.rodcollab.androidrodrigocavalcante.utils.DateUtils

class OrdersHistoryAdapter(
) : RecyclerView.Adapter<OrdersHistoryAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<OrderHistory> = AsyncListDiffer(this, DiffCallback)
    private lateinit var legendaAdapter:LegendaAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OrderItemBinding.inflate(layoutInflater, parent, false)
        legendaAdapter = LegendaAdapter()
        return ViewHolder(binding,legendaAdapter)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    fun updateOrders(orders: List<OrderHistory>) {
        asyncListDiffer.submitList(orders)
    }

    class ViewHolder(
        private val binding: OrderItemBinding,
        private val legendaAdapter: LegendaAdapter
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(order: OrderHistory) {
            binding.orderNumber.text = "${order.numero_ped_Rca} / ${order.numero_ped_erp}"
            binding.date.text = "${DateUtils().getDateOrderFormatted(order.data)}"
            binding.codeAndClient.text = "${order.codigoCliente} - ${order.NOMECLIENTE}"
            binding.status.text = order.status

            when(order.critica) {
                "SUCESSO" -> {
                    binding.imgCritica.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.ic_maxima_critica_sucesso))
                }
                "FALHA_PARCIAL" -> {
                    binding.imgCritica.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.ic_maxima_critica_alerta))
                }
                else -> {
                    binding.critica.visibility = View.INVISIBLE
                }
            }

            binding.rvLegendas.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
            binding.rvLegendas.layoutDirection
            binding.rvLegendas.adapter = legendaAdapter

            order.legendas?.let {
                val legendas = it.map { valueLegenda ->
                    Legenda(
                        value = valueLegenda
                    )
                }
                legendaAdapter.setLegendas(legendas.toList())
            }

            when(order.status) {
                "Processado" -> {
                    binding.bgStatus.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context,R.color.liberado))
                    binding.imgStatus.visibility = View.INVISIBLE
                    binding.statusText.visibility = View.VISIBLE
                    binding.statusText.text = "L"
                }
                "Em processamento" -> {
                    binding.bgStatus.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context,R.color.em_processamento))
                    binding.imgStatus.visibility = View.VISIBLE
                    binding.statusText.visibility = View.INVISIBLE
                    binding.imgStatus.setImageDrawable(ContextCompat.getDrawable(binding.root.context,R.drawable.ic_maxima_em_processamento))
                }
                "Pendente" -> {
                    binding.bgStatus.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context,R.color.pendente))
                    binding.imgStatus.visibility = View.INVISIBLE
                    binding.statusText.visibility = View.VISIBLE
                    binding.statusText.text = "P"
                }
                "Em Processamento" -> {
                    binding.bgStatus.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context,R.color.em_processamento))
                    binding.imgStatus.visibility = View.VISIBLE
                    binding.statusText.visibility = View.INVISIBLE
                    binding.imgStatus.setImageDrawable(ContextCompat.getDrawable(binding.root.context,R.drawable.ic_maxima_em_processamento))
                }
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<OrderHistory>() {

        override fun areItemsTheSame(oldItem: OrderHistory, newItem: OrderHistory): Boolean {
            return oldItem.codigoCliente == newItem.codigoCliente
        }

        override fun areContentsTheSame(oldItem: OrderHistory, newItem: OrderHistory): Boolean {
            return oldItem.codigoCliente == newItem.codigoCliente
        }
    }
}