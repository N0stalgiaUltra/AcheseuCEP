package com.n0stalgiaultra.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.n0stalgiaultra.data.local.CepLocal
import com.n0stalgiaultra.data.remote.CepDto
import com.n0stalgiaultra.myapplication.R
import com.n0stalgiaultra.myapplication.databinding.FragmentCepResultBinding
import com.n0stalgiaultra.view.adapters.CardAdapter
import com.n0stalgiaultra.view.adapters.CardOnClick
import com.n0stalgiaultra.view.adapters.CardOnClickImpl
import com.n0stalgiaultra.view.utils.FragmentIdHandler
import com.n0stalgiaultra.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class CepResult : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModel()
    private lateinit var idHandler : FragmentIdHandler

    private lateinit var binding: FragmentCepResultBinding
    private val cardOnClick by lazy { CardOnClickImpl(mainViewModel) }
    private val cardAdapter by lazy { CardAdapter(cardOnClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idHandler = FragmentIdHandler(requireActivity())
        Log.d("SearchCep", idHandler.getID().toString())


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCepResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idHandler.saveID(R.id.cepResult)
        mainViewModel.cepList.observe(viewLifecycleOwner){
            items ->
                if(items.isNotEmpty()){
                Log.d("CepResult", cardAdapter.itemCount.toString())
                    cardAdapter.clearData()
                    cardAdapter.setData(items)
                    setupRecyclerView()
                    binding.loadingScreen.visibility = View.INVISIBLE
                }
                else{
                    Log.d("CepResult", "Lista Vazia")
                }
        }
    }

    private fun setupRecyclerView(){
        binding.mainRecView.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = cardAdapter
        }
    }


}