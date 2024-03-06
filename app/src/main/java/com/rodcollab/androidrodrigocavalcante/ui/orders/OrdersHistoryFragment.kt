package com.rodcollab.androidrodrigocavalcante.ui.orders

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rodcollab.androidrodrigocavalcante.R
import com.rodcollab.androidrodrigocavalcante.databinding.FragmentOrdersHistoryBinding
import com.rodcollab.androidrodrigocavalcante.ui.adapter.ContactsAdapter
import com.rodcollab.androidrodrigocavalcante.ui.client.ClientInfoLifecycleObserver
import com.rodcollab.androidrodrigocavalcante.ui.client.ClientInfoVm

class OrdersHistoryFragment : Fragment() {

    private var _binding: FragmentOrdersHistoryBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
