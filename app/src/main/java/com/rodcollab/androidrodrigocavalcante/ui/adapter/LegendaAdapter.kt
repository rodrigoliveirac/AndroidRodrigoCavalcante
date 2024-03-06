package com.rodcollab.androidrodrigocavalcante.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rodcollab.androidrodrigocavalcante.R
import com.rodcollab.androidrodrigocavalcante.databinding.LegendaItemBinding
import java.util.UUID

data class Legenda(
    val uuid: UUID = UUID.randomUUID(),
    val value:String = ""
)
class LegendaAdapter(
) : RecyclerView.Adapter<LegendaAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<Legenda> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LegendaItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position].value)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    fun setLegendas(legendas: List<Legenda>) {
        asyncListDiffer.submitList(legendas)
    }

    class ViewHolder(
        private val binding: LegendaItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(legenda: String) {
           when(legenda) {
                  "PEDIDO_SOFREU_CORTE" -> {
                      binding.icLegenda.setImageDrawable(ContextCompat.getDrawable(binding.root.context,R.drawable.ic_maxima_legenda_corte))
                  }
                   "PEDIDO_FEITO_TELEMARKETING" -> {
                       binding.icLegenda.setImageDrawable(ContextCompat.getDrawable(binding.root.context,R.drawable.ic_maxima_legenda_telemarketing))
                   }
                   "PEDIDO_CANCELADO_ERP" -> {
                       binding.icLegenda.setImageDrawable(ContextCompat.getDrawable(binding.root.context,R.drawable.ic_maxima_legenda_cancelamento))
                   }
           }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Legenda>() {

        override fun areItemsTheSame(oldItem: Legenda, newItem: Legenda): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: Legenda, newItem: Legenda): Boolean {
            return oldItem.uuid == newItem.uuid
        }
    }
}
