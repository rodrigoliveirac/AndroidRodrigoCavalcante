package com.rodcollab.androidrodrigocavalcante.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rodcollab.androidrodrigocavalcante.ui.adapter.ContactsAdapter
import com.rodcollab.androidrodrigocavalcante.utils.DateUtils
import com.rodcollab.androidrodrigocavalcante.R
import com.rodcollab.androidrodrigocavalcante.databinding.FragmentClientInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientInfoFragment : Fragment() {

    private var _binding: FragmentClientInfoBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: ContactsAdapter

    private lateinit var viewModel: ClientInfoVm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[ClientInfoVm::class.java]
        lifecycle.addObserver(ClientInfoLifecycleObserver(viewModel))
        adapter = ContactsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clientContactsView.rvContacts.layoutManager = LinearLayoutManager(requireContext())
        binding.clientContactsView.rvContacts.adapter = adapter

        viewModel.uiState.observe(viewLifecycleOwner) { clientUiState ->
            clientUiState.client?.let { client ->
                    binding.dataClient.corporateName.text = "${client.codigo} - ${client.razao_social}"
                    binding.dataClient.tradeName.text = client.nomeFantasia
                    binding.dataClient.cnpj.text = client.cnpj
                    binding.dataClient.industry.text = client.ramo_atividade
                    binding.dataClient.address.text = client.endereco
                    adapter.updateContacts(client.contatos.toList())
                    binding.buttonShowStatus.setOnClickListener {

                        Snackbar
                            .make(view, "${DateUtils().currentDateAndTime} Status ${client.status}", Snackbar.LENGTH_SHORT)
                            .setAction("FECHAR") { }
                            .setActionTextColor(requireContext().getColor(R.color.text_status))
                            .show()
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}