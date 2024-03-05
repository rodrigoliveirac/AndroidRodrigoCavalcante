package com.rodcollab.androidrodrigocavalcante

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rodcollab.androidrodrigocavalcante.databinding.ItemContactBinding

class ContactsAdapter(
) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<Contact> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    fun updateContacts(contacts: List<Contact>) {
        asyncListDiffer.submitList(contacts)
    }

    class ViewHolder(
        private val binding: ItemContactBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            binding.clientName.text = contact.nome
            binding.phoneClient.text = contact.telefone
            binding.email.text = contact.e_mail
            binding.cellphone.text = contact.celular
            binding.birthday.text = contact.data_nascimento
            binding.spouseName.text = contact.conjuge
            binding.spouseBirthday.text = contact.dataNascimentoConjuge
            binding.type.text = contact.tipo
            binding.team.text = contact.time
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Contact>() {

        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.nome == newItem.nome
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.nome == newItem.nome
        }
    }
}