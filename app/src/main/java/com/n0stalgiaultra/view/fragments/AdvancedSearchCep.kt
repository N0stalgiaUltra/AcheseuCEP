package com.n0stalgiaultra.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.myapplication.databinding.FragmentAdvancedSearchCepBinding
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class AdvancedSearchCep : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModel()
    private lateinit var binding: FragmentAdvancedSearchCepBinding
    private var state: String = ""
    private var city: String = ""
    private var street: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdvancedSearchCepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBuscaCEP.setOnClickListener {
            state = binding.etEstado.text.toString()
            city = binding.etCidade.text.toString()
            street = binding.etRua.text.toString()

            CoroutineScope(Dispatchers.Main).launch {
                mainViewModel.getCepList(state, city, street)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.cepList.observe(this){
            items ->
            if(items.isNotEmpty()){
                binding.btnBuscaCEP.setOnClickListener {
                    findNavController().navigate(R.id.cepResult)
                }
            }

        }
    }
}