package com.n0stalgiaultra.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.myapplication.databinding.FragmentMainScreenBinding


class MainScreen : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        binding.btnBuscaavancadaocep.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_advancedSearchCep)
        }

        binding.btnBuscapadraocep.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_searchCep)
        }
    }

}