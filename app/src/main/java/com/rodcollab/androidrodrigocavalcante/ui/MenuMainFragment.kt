package com.rodcollab.androidrodrigocavalcante.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rodcollab.androidrodrigocavalcante.R
import com.rodcollab.androidrodrigocavalcante.databinding.FragmentMenuMainBinding

class MenuMainFragment : Fragment() {

    private var _binding: FragmentMenuMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardViewButtonClients.setOnClickListener {
            findNavController().navigate(R.id.action_menuMain_to_clientInfo)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}