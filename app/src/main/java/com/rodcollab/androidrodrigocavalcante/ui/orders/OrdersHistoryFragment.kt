package com.rodcollab.androidrodrigocavalcante.ui.orders

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.rodcollab.androidrodrigocavalcante.R
import com.rodcollab.androidrodrigocavalcante.databinding.FragmentOrdersHistoryBinding
import com.rodcollab.androidrodrigocavalcante.ui.adapter.ContactsAdapter
import com.rodcollab.androidrodrigocavalcante.ui.adapter.OrdersHistoryAdapter
import com.rodcollab.androidrodrigocavalcante.ui.client.ClientInfoLifecycleObserver
import com.rodcollab.androidrodrigocavalcante.ui.client.ClientInfoVm
import com.rodcollab.androidrodrigocavalcante.ui.client.convertDpToPixel
import com.rodcollab.androidrodrigocavalcante.utils.DateUtils
import dagger.hilt.android.AndroidEntryPoint

class OrdersHistoryObserver(private val viewModel: OrdersHistoryVm):DefaultLifecycleObserver {
    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        viewModel.onResume()
    }
}
@AndroidEntryPoint
class OrdersHistoryFragment : Fragment() {

    private var _binding: FragmentOrdersHistoryBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel:OrdersHistoryVm
    private lateinit var adapter:OrdersHistoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[OrdersHistoryVm::class.java]
        adapter = OrdersHistoryAdapter()
        lifecycle.addObserver(OrdersHistoryObserver(viewModel))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvOrdersHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrdersHistory.adapter = adapter

        addingDividerDecoration()

        viewModel.uiState.observe(viewLifecycleOwner) { ordersHistoryUiState ->
            ordersHistoryUiState.history?.let { order ->
                adapter.updateOrders(order)
            }
        }



    }
    private fun addingDividerDecoration() {
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        divider.isLastItemDecorated = false

        val resources = requireContext().resources

        divider.dividerInsetStart = resources.getDimensionPixelSize(R.dimen.horizontal_margin)

        divider.dividerThickness = resources.getDimensionPixelSize(R.dimen.divider_height)
        divider.dividerColor = ContextCompat.getColor(requireContext(), R.color.light_gray)

        binding.rvOrdersHistory.addItemDecoration(divider)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
