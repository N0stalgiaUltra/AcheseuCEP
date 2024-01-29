package com.n0stalgiaultra.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.n0stalgiaultra.database.utils.FragmentIdHandler
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.myapplication.databinding.FragmentSearchCepBinding
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel



class SearchCep : Fragment() {

    private lateinit var binding: FragmentSearchCepBinding
    private val viewModel: MainViewModel by activityViewModel()
    private lateinit var idHandler : FragmentIdHandler

    private var cepText: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idHandler = FragmentIdHandler(requireActivity())
        Log.d("SearchCep", this.id.toString())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchCepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idHandler.saveID(R.id.searchCep)
        // 20541110, 20541-110
        // 20541110, 20551150
        binding.btnBuscaCEP.setOnClickListener {
            cepText = binding.edDigiteCep.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.getDataFromCep(cepText)
            }
            findNavController().navigate(R.id.action_searchCep_to_cepResult)
        }
    }
}