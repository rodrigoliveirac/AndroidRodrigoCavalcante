package com.rodcollab.androidrodrigocavalcante.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.fragment.app.Fragment
import com.rodcollab.androidrodrigocavalcante.R
import com.rodcollab.androidrodrigocavalcante.databinding.FragmentAlvarasBinding

class AlvarasFragment : Fragment() {

    private var _binding: FragmentAlvarasBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlvarasBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.align(Alignment.Center), verticalArrangement = Arrangement.Center) {
                            Image(modifier = Modifier.align(Alignment.CenterHorizontally),painter = painterResource(id = R.drawable.maxima_empty_state), contentDescription = null)
                            Text(fontWeight = FontWeight(weight = 500),style = MaterialTheme.typography.titleMedium, modifier = Modifier.fillMaxWidth(),text= "Nada encontrado...", textAlign = TextAlign.Center)
                            Text(style = MaterialTheme.typography.bodyMedium,modifier = Modifier.fillMaxWidth(),text= "Não há alvarás para exibir,",textAlign = TextAlign.Center)
                            Text(style = MaterialTheme.typography.bodyMedium,modifier = Modifier.fillMaxWidth(),text = "verifique com o responsável pelo ERP",textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}