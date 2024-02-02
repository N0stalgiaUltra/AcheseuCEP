package com.n0stalgiaultra.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.n0stalgiaultra.database.utils.FragmentIdHandler
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.myapplication.databinding.FragmentAdvancedSearchCepBinding
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AdvancedSearchCep : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModel()
    private lateinit var idHandler : FragmentIdHandler

    private lateinit var binding: FragmentAdvancedSearchCepBinding
    private var state: String = ""
    private var city: String = ""
    private var street: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idHandler = FragmentIdHandler(requireActivity())

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
        idHandler.saveID(R.id.advancedSearchCep)

        setSpinner()
        binding.btnBuscaCEP.setOnClickListener {
            //state = binding.etEstado.text.toString()
            city = binding.etCidade.text.toString()
            street = binding.etRua.text.toString()

            CoroutineScope(Dispatchers.Main).launch {
                mainViewModel.getCepList(state, city, street)
            }
            findNavController().navigate(R.id.action_advancedSearchCep_to_cepResult)

        }
    }

    private fun setSpinner(){
        mainViewModel.statesList.observe(viewLifecycleOwner){
            items ->

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                items)

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.stateSpinner.adapter = adapter
            binding.stateSpinner.setSelection(0, false)
            
            binding.stateSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItem: View?,
                    position: Int, id: Long) {

                    if(position > 0){
                        val selectedState = items[position]
                        state = selectedState
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }


    }
}