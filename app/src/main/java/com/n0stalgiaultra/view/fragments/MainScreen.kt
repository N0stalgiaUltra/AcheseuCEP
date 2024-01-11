package com.n0stalgiaultra.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.myapplication.databinding.FragmentMainScreenBinding
import com.n0stalgiaultra.utils.FragmentIdHandler


class MainScreen : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var idHandler : FragmentIdHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idHandler = FragmentIdHandler(requireActivity())
        idHandler.saveID(this.id)
        Log.d("SearchCep", this.id.toString())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idHandler.saveID(R.id.mainScreen)
        binding.btnBuscaavancadaocep.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_advancedSearchCep)
        }

        binding.btnBuscapadraocep.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_searchCep)
        }
    }

}